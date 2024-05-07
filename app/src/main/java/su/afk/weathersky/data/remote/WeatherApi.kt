package su.afk.weathersky.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import su.afk.weathersky.data.remote.models.WeatherDto


interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherDto
}