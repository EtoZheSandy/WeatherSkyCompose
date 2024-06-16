package su.afk.weathersky

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.AndroidEntryPoint
import su.afk.weathersky.presentation.WeatherState
import su.afk.weathersky.presentation.WeatherViewModel
import su.afk.weathersky.presentation.components.ErrorScreen
import su.afk.weathersky.presentation.components.LoadingScreen
import su.afk.weathersky.presentation.components.WeatherMainScreen
import su.afk.weathersky.presentation.ui.theme.WeatherSkyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels<WeatherViewModel>()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )

        setContent {
            WeatherSkyTheme {
                val context = LocalContext.current
                val stateWeather = viewModel.stateWeather
                val stateNextDay = viewModel.stateNextDay

                Box(modifier = Modifier.fillMaxSize()) {
                    when(stateWeather) {
                        is WeatherState.ErrorState -> {
                            ErrorScreen(
                                errorText = stateWeather.error!!,
                                context = context,
                                onRetryClick = { viewModel.loadWeatherInfo() }
                            )
                        }
                        is WeatherState.LoadingState -> {
                            LoadingScreen(modifier = Modifier.align(Alignment.Center))
                        }
                        is WeatherState.WeatherInfoState -> {
                            WeatherMainScreen(stateWeather = stateWeather.weatherInfo!!,
                                stateNextDay = stateNextDay)
                        }
                    }
                }
            }
        }
    }

    // если перешли в настройки и дали разрешение то при возврате делаем еще один запрос
    override fun onResume() {
        viewModel.loadWeatherInfo()
        super.onResume()
    }
}

