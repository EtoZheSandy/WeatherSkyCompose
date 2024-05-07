package su.afk.weathersky.data.repository

import com.plcoding.weatherapp.domain.util.Resource
import su.afk.weathersky.data.remote.WeatherApi
import su.afk.weathersky.domain.repository.WeatherRepository
import su.afk.weathersky.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        TODO("Not yet implemented")
    }
}