package com.example.weatherapp.weeklyWeather.screen.weekly

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.domain.model.WeeklyWeather
import com.example.weatherapp.R
import com.example.weatherapp.common.components.LoadingScreen
import com.example.weatherapp.weeklyWeather.uiState.WeeklyWeatherUIState

@Composable
fun WeeklyWeatherScreen(
    paddingValues: PaddingValues,
    weeklyWeatherUIState: WeeklyWeatherUIState,
    formatDay: (Long) -> String,
    formatHour: (Long) -> String
) {

    when (weeklyWeatherUIState) {

        WeeklyWeatherUIState.Loading -> LoadingScreen()

        WeeklyWeatherUIState.Empty -> {}

        is WeeklyWeatherUIState.Success -> WeeklyScreen(
            paddingValues = paddingValues,
            weeklyWeather = weeklyWeatherUIState.data,
            formatDay = formatDay,
            formatHour = formatHour
        )

        is WeeklyWeatherUIState.Error -> {}

    }
}

@Composable
fun WeeklyScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    weeklyWeather: List<WeeklyWeather>,
    formatDay: (Long) -> String,
    formatHour: (Long) -> String
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .padding(dimensionResource(id = R.dimen.smallPadding))
    ) {
        WeeklyHeader(
            city = weeklyWeather.first().city,
            countryCode = weeklyWeather.first().countryCode
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.mediumHeight)))

        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            items(weeklyWeather) { weeklyItem: WeeklyWeather ->
                WeeklyItem(
                    weeklyItem = weeklyItem,
                    formatDay = formatDay,
                    formatHour = formatHour
                )
            }
        }
    }

}


@Composable
fun WeeklyHeader(
    city: String,
    countryCode: String,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.smallPadding)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {

            Text(
                text = stringResource(R.string.comma, city),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize

            )
            Text(
                text = countryCode,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
        }
        Text(
            text = stringResource(R.string.nextWeek),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Thin,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }


}


@Composable
fun WeeklyItem(
    weeklyItem: WeeklyWeather,
    formatDay: (Long) -> String,
    formatHour: (Long) -> String
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.smallPadding))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.largeCornerRadius)))
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.inversePrimary
                    )
                )
            )
            .padding(dimensionResource(id = R.dimen.smallPadding)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        AsyncImage(
            model = weeklyItem.icon,
            contentDescription = stringResource(R.string.weatherIcon),
        )

        Text(
            text = formatDay.invoke(weeklyItem.time),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(R.string.maxTemp),
            fontWeight = FontWeight.Bold
        )
        Text(text = stringResource(R.string.celsius, weeklyItem.maxTemp.toString()))

        Text(
            text = stringResource(R.string.minTemp),
            fontWeight = FontWeight.Bold
        )
        Text(text = stringResource(id = R.string.celsius, weeklyItem.minTemp.toString()))

        Text(
            text = stringResource(R.string.sunset),
            fontWeight = FontWeight.Bold
        )
        Text(text = formatHour.invoke(weeklyItem.sunset))

        Text(
            text = stringResource(R.string.sunrise),
            fontWeight = FontWeight.Bold
        )
        Text(text = formatHour.invoke(weeklyItem.sunrise))

        Text(
            text = stringResource(R.string.windSpeed),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.windSpeedUnits, weeklyItem.windSpeed.toString())
        )
    }

}
