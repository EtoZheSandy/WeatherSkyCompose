package su.afk.weathersky.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import su.afk.weathersky.domain.weather.WeatherData
import su.afk.weathersky.domain.weather.WeatherInfo
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecast(
    state: WeatherInfo,
    modifier: Modifier = Modifier,
) {
    state?.weatheredDataPerDay?.get(0)?.let { currentDay ->

        val currentTime = LocalTime.now()
        val roundedTime = if (currentTime.minute < 30) {
            currentTime.withMinute(30).withSecond(0)
        } else {
            currentTime.withHour(currentTime.hour).withMinute(0).withSecond(0)
        }

        val currentIndex = currentDay.indexOfFirst {
            it.time.toLocalTime() >= roundedTime
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Today",
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                // для начальной позиции скролла
                state = rememberLazyListState(initialFirstVisibleItemIndex = currentIndex),
                content = {
                    itemsIndexed(currentDay) { index, weatherData ->
                        HourWeatherListItem(
                            weatherData = weatherData,
                            modifier = Modifier
                                .height(100.dp)
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun HourWeatherListItem(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
) {
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            color = Color.LightGray
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}
