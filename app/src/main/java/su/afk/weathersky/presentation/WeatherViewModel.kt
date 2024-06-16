package su.afk.weathersky.presentation

import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import su.afk.weathersky.domain.location.LocationTracker
import su.afk.weathersky.domain.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    var stateWeather by mutableStateOf<WeatherState>(WeatherState.LoadingState())
        private set

    var stateNextDay by mutableStateOf<NextDayState>(NextDayState.LoadingState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            stateWeather = WeatherState.LoadingState()
            stateNextDay = NextDayState.LoadingState()

            locationTracker.getCurrentLocation()?.let { location ->
                Log.d("loadWeatherInfo", "location: $location")

                getCurrentWeather(location)
                getNextDays(location)
            } ?: kotlin.run {
                stateWeather = WeatherState.ErrorState("Не удалось загрузить данные")
                stateNextDay = NextDayState.ErrorState("Не удалось загрузить данные")
            }
        }
    }

    private fun getCurrentWeather(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultCurrentWeather = repository.getWeatherData(location.latitude, location.longitude)

            when(resultCurrentWeather) {
                is Resource.Success -> {
                    stateWeather = WeatherState.WeatherInfoState(resultCurrentWeather.data)
                }
                is Resource.Error -> {
                    stateWeather = WeatherState.ErrorState(resultCurrentWeather.message)
                }
            }
        }
    }

    private fun getNextDays(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            val resultNextDays = repository.getNextDaysData(location.latitude, location.longitude)

            when(resultNextDays) {
                is Resource.Success -> {
                    stateNextDay = NextDayState.WeatherInfoState(resultNextDays.data)
                }
                is Resource.Error -> {
                    stateNextDay = NextDayState.ErrorState(resultNextDays.message)
                }
            }
        }
    }

}