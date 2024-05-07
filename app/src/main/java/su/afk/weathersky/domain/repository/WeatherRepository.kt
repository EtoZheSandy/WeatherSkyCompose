package su.afk.weathersky.domain.repository

import com.plcoding.weatherapp.domain.util.Resource
import su.afk.weathersky.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double) : Resource<WeatherInfo>

}