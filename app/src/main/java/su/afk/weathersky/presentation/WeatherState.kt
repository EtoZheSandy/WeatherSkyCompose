package su.afk.weathersky.presentation

import su.afk.weathersky.domain.weather.NextDays
import su.afk.weathersky.domain.weather.WeatherInfo

sealed class WeatherState() {
    data class WeatherInfoState(
        val weatherInfo: WeatherInfo? = null,
    ) : WeatherState()
    data class LoadingState(
        val isLoading: Boolean = false,
    ) : WeatherState()
    data class ErrorState(
        val error: String? = null,
    ) : WeatherState()
}

sealed class NextDayState() {
    data class WeatherInfoState(
        val nextDays: NextDays? = null,
    ) : NextDayState()
    data class LoadingState(
        val isLoadingNextDays: Boolean = false,
    ) : NextDayState()
    data class ErrorState(
        val errorNextDays: String? = null
    ) : NextDayState()
}