package su.afk.weathersky.domain.weather

import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Int,
    val weatherType: WeatherType,
)
