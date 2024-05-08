package su.afk.weathersky.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import su.afk.weathersky.data.remote.models.WeatherDto


interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,relative_humidity_2m,wind_speed_10m,pressure_msl,weather_code")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherDto
}