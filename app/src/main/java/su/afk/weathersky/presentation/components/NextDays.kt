package su.afk.weathersky.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import su.afk.weathersky.presentation.WeatherState

@Composable
fun NextDays(
    modifier: Modifier = Modifier,
    state: WeatherState,
) {
    state.nextDays?.let { nextDays ->
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            items(nextDays.oneDayData) { day ->
                NextDaysItem(day = day)
            }
        }
    }
}
