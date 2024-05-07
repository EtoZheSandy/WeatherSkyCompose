package su.afk.weathersky.domain.weather

data class WeatherInfo(
    val weatherdDataPerDay: Map<Int, List<WeatherData>>, // int это день а List это часы в этом дне
    val currentWeatherData: WeatherData?, //текущий день и час
)