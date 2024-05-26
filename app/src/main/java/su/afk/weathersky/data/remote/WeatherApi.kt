package su.afk.weathersky.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import su.afk.weathersky.data.remote.models.WeatherDto
import su.afk.weathersky.data.remote.models.nextDays.NextDaysResponse


interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,relative_humidity_2m,wind_speed_10m,pressure_msl,weather_code")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherDto

    @GET("v1/forecast?daily=weather_code,temperature_2m_max,temperature_2m_min")
    suspend fun getNextDays(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): NextDaysResponse
}