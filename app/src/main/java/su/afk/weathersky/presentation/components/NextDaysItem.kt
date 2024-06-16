package su.afk.weathersky.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import su.afk.weathersky.domain.weather.OneDayData
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun NextDaysItem(
    day: OneDayData,
) {
    val formattedTime = remember(day) {
        val date = LocalDate.parse(day.day)
        val formatter = DateTimeFormatter.ofPattern("dd/MM")
        date.format(formatter)
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(painter = painterResource(id = day.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${day.maxTemperatureCelsius}/${day.minTemperatureCelsius}°C",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = day.weatherType.weatherDesc,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.weight(1f))
            Text(
                textAlign = TextAlign.End,
                text = formattedTime,
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }
    }

}