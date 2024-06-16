package su.afk.weathersky.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import su.afk.weathersky.R
import su.afk.weathersky.domain.weather.WeatherInfo
import su.afk.weathersky.presentation.WeatherState
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherInfo,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    state?.currentWeatherData?.let { weatherData ->
        Card(
            colors = CardDefaults.cardColors(backgroundColor),
            shape = RoundedCornerShape(20.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth() // Занимаем всю ширину
                ) {
                    Text(
                        text = "Today ${
                            weatherData.time.format(
                                DateTimeFormatter.ofPattern("HH:mm")
                            )
                        }",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = weatherData.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(180.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${weatherData.temperatureCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = weatherData.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplayRow(
                        value = weatherData.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(
                            id = R.drawable.ic_pressure
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(Color.White)
                    )
                    WeatherDataDisplayRow(
                        value = weatherData.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(
                            id = R.drawable.ic_wind
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(Color.White)
                    )
                    WeatherDataDisplayRow(
                        value = weatherData.humidity, unit = "&", icon = ImageVector.vectorResource(
                            id = R.drawable.ic_drop
                        ), iconTint = Color.White, textStyle = TextStyle(Color.White)
                    )
                }
            }
        }
    }
}


@Composable
fun WeatherDataDisplayRow(
    value: Int,
    unit: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    iconTint: Color = Color.White,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$value$unit",
            style = textStyle
        )
    }
}