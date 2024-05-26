package su.afk.weathersky.data.mappers

import com.plcoding.weatherapp.domain.weather.WeatherType
import su.afk.weathersky.data.remote.models.WeatherDto
import su.afk.weathersky.domain.weather.WeatherData
import su.afk.weathersky.domain.weather.WeatherInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexWeatherData(
    val index: Int,
    val data: WeatherData
)


fun WeatherDto.toWeatherDataMap() : Map<Int, List<WeatherData>> {
    return hourly.time.mapIndexed { index, time ->
        val temperature = hourly.temperature_2m[index]
        val weatherCode = hourly.weather_code[index]
        val windSpeed = hourly.wind_speed_10m[index]
        val pressure = hourly.pressure_msl[index]
        val humidity = hourly.relative_humidity_2m[index]
        //длинна у списков одинаковая поэтому можем использовать index
        IndexWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                windSpeed = windSpeed,
                pressure = pressure,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24 // делим на 24 что бы получить количество дней и упорядосить данные по каждому дню
    }.mapValues { it: Map.Entry<Int, List<IndexWeatherData>> ->
        it.value.map {
            it.data
        }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = this.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatheredDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}