package su.afk.weathersky.data.mappers

import com.plcoding.weatherapp.domain.weather.WeatherType
import su.afk.weathersky.data.remote.models.nextDays.NextDaysResponse
import su.afk.weathersky.domain.weather.NextDays
import su.afk.weathersky.domain.weather.OneDayData

fun NextDaysResponse.toNextDaysMapper(): NextDays {

    val nextDayMap = List(this.daily.time.size) { index ->
        OneDayData(
            day = this.daily.time[index],
            minTemperatureCelsius = this.daily.temperature_2m_min[index],
            maxTemperatureCelsius = this.daily.temperature_2m_max[index],
            weatherType = WeatherType.fromWMO(this.daily.weather_code[index])
        )
    }

    return NextDays(oneDayData = nextDayMap)
}