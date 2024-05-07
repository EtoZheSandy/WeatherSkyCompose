package su.afk.weathersky.data.remote.models

data class CurrentUnits(
    val interval: String,
    val temperature_2m: String,
    val time: String,
    val wind_speed_10m: String
)