package su.afk.weathersky.data.remote.models

data class HourlyUnits(
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val wind_speed_10m: String
)