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
                Box(modifier = Modifier.fillMaxSize()) {
                    when {
                        viewModel.state.isLoading -> {
                            LoadingScreen(modifier = Modifier.align(Alignment.Center))
                        }

                        viewModel.state.error != null -> {
                            ErrorScreen(
                                errorText = viewModel.state.error ?: "None",
                                context = context,
                                onRetryClick = { viewModel.loadWeatherInfo() }
                            )
                        }

                        viewModel.state.weatherInfo != null -> {
                            WeatherMainScreen(state = viewModel.state)
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        viewModel.loadWeatherInfo()
        super.onResume()
    }
}

