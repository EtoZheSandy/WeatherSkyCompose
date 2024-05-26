package su.afk.weathersky.domain.repository

import com.plcoding.weatherapp.domain.util.Resource
import su.afk.weathersky.data.remote.models.nextDays.NextDaysResponse
import su.afk.weathersky.domain.weather.NextDays
import su.afk.weathersky.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double) : Resource<WeatherInfo>

    suspend fun getNextDaysData(lat: Double, long: Double) : Resource<NextDays>
}