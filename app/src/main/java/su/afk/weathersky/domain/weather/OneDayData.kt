package su.afk.weathersky.domain.weather

import com.plcoding.weatherapp.domain.weather.WeatherType

data class OneDayData (
    val day: String,
    val minTemperatureCelsius: Double,
    val maxTemperatureCelsius: Double,
    val weatherType: WeatherType
)