package su.afk.weathersky.data.remote.models

data class Current(
    val interval: Int,
    val temperature_2m: Double,
    val time: String,
    val wind_speed_10m: Int
)