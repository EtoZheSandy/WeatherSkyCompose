package su.afk.weathersky.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import su.afk.weathersky.domain.weather.WeatherInfo
import su.afk.weathersky.presentation.NextDayState
import su.afk.weathersky.presentation.ui.theme.DarkBlue
import su.afk.weathersky.presentation.ui.theme.LightBlue

@Composable
fun WeatherMainScreen(
    stateWeather: WeatherInfo,
    stateNextDay: NextDayState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue)
            .padding(top = 40.dp)
    ) {
        WeatherCard(
            state = stateWeather,
            backgroundColor = DarkBlue
        )

        WeatherForecast(state = stateWeather)

        Spacer(modifier = Modifier.height(8.dp))

        NextDays(stateNextDay = stateNextDay)
    }
}