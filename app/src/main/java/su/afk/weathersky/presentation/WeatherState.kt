package su.afk.weathersky.presentation

import su.afk.weathersky.domain.weather.NextDays
import su.afk.weathersky.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val nextDays: NextDays? = null,
    val isLoading: Boolean = false,
    val isLoadingNextDays: Boolean = false,
    val error: String? = null,
    val errorNextDays: String? = null
)