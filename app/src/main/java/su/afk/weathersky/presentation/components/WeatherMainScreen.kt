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
import su.afk.weathersky.presentation.WeatherState
import su.afk.weathersky.presentation.WeatherViewModel
import su.afk.weathersky.presentation.ui.theme.DarkBlue
import su.afk.weathersky.presentation.ui.theme.LightBlue

@Composable
fun WeatherMainScreen(
    modifier: Modifier = Modifier,
    state: WeatherState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue)
            .padding(top = 40.dp)
    ) {
        WeatherCard(
            state = state,
            backgroundColor = DarkBlue
        )
        Spacer(modifier = Modifier.height(16.dp))

        WeatherForecast(state = state)
        // TODO: Скролить до нужного часа а те часы что прошли что бы были слева

        // TODO: Снизу сделать вывод на следующие 3 дня в виде трех кнопок
        // TODO: При клике открывать новую страничку где будут показываться часы и темпа в column

    }
}