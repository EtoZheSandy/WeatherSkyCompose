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

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                Log.d("loadWeatherInfo", "location: $location")

                getCurrentWeather(location)
                getNextDays(location)
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Не удалось получить местоположение"
                )
            }
        }
    }

    private fun getCurrentWeather(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultCurrentWeather = repository.getWeatherData(location.latitude, location.longitude)

            when(resultCurrentWeather) {
                is Resource.Success -> {
                    state = state.copy(
                        weatherInfo = resultCurrentWeather.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        weatherInfo = null,
                        isLoading = false,
                        error = resultCurrentWeather.message
                    )
                }
            }
        }
    }

    private fun getNextDays(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            val resultNextDays = repository.getNextDaysData(location.latitude, location.longitude)

            when(resultNextDays) {
                is Resource.Success -> {
                    state = state.copy(
                        nextDays = resultNextDays.data,
                        isLoadingNextDays = false,
                        errorNextDays = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        nextDays = null,
                        isLoadingNextDays = false,
                        errorNextDays = resultNextDays.message
                    )
                }
            }
        }
    }

}