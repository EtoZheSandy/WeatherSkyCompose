package su.afk.weathersky.data.repository

import android.util.Log
import com.plcoding.weatherapp.domain.util.Resource
import su.afk.weathersky.data.mappers.toNextDaysMapper
import su.afk.weathersky.data.mappers.toWeatherInfo
import su.afk.weathersky.data.remote.WeatherApi
import su.afk.weathersky.domain.repository.WeatherRepository
import su.afk.weathersky.domain.weather.NextDays
import su.afk.weathersky.domain.weather.WeatherInfo
import javax.inject.Inject

class NetworkWeatherRepository @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        Log.d("NetworkWeatherRepository", "getWeatherData: $lat $long")
        return try {
            Resource.Success(
                data = api.getWeather(
                    latitude = lat,
                    longitude = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: ERROR_TEXT)
        }
    }

    override suspend fun getNextDaysData(lat: Double, long: Double): Resource<NextDays> {
        Log.d("NetworkWeatherRepository", "getNextDaysData: $lat $long")
        return try {
            val nextDaysData = api.getNextDays(latitude = lat, longitude = long).toNextDaysMapper()
            Resource.Success(data = nextDaysData)
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: ERROR_TEXT)
        }
    }

    companion object {
        const val ERROR_TEXT = "Произошла неизвественая ошибка"
    }
}