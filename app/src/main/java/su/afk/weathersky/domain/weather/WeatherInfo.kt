package su.afk.weathersky.domain.weather

data class WeatherInfo(
    val weatheredDataPerDay: Map<Int, List<WeatherData>>, // int это день а List это часы в этом дне
    val currentWeatherData: WeatherData?, //текущий день и час
)