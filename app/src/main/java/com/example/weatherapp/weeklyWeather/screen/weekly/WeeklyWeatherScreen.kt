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
import com.example.weatherapp.common.components.ErrorScreen
import com.example.weatherapp.common.components.LoadingScreen
import com.example.weatherapp.weeklyWeather.model.WeeklyWeatherUIModel
import com.example.weatherapp.weeklyWeather.uiState.WeeklyWeatherUIState

@Composable
fun WeeklyWeatherScreen(
    paddingValues: PaddingValues,
    weeklyWeatherUIState: WeeklyWeatherUIState
) {

    when (weeklyWeatherUIState) {

        WeeklyWeatherUIState.Loading -> LoadingScreen()

        WeeklyWeatherUIState.Empty -> {}

        is WeeklyWeatherUIState.Success -> WeeklyScreen(
            paddingValues = paddingValues,
            weeklyWeather = weeklyWeatherUIState.data
        )

        is WeeklyWeatherUIState.Error -> {
            when(weeklyWeatherUIState){
                is WeeklyWeatherUIState.Error.Internet -> ErrorScreen(message = weeklyWeatherUIState.message)
                is WeeklyWeatherUIState.Error.Server -> ErrorScreen(message = weeklyWeatherUIState.message)
                is WeeklyWeatherUIState.Error.Unknown -> ErrorScreen(message = weeklyWeatherUIState.message)
            }
        }

    }
}

@Composable
fun WeeklyScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    weeklyWeather: List<WeeklyWeatherUIModel>,
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

            items(weeklyWeather) { weeklyItem: WeeklyWeatherUIModel ->
                WeeklyItem(
                    weeklyItem = weeklyItem
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
    weeklyItem: WeeklyWeatherUIModel
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
            text = weeklyItem.day,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(R.string.maxTemp),
            fontWeight = FontWeight.Bold
        )
        Text(text = stringResource(R.string.celsius, weeklyItem.maxTemp))

        Text(
            text = stringResource(R.string.minTemp),
            fontWeight = FontWeight.Bold
        )
        Text(text = stringResource(id = R.string.celsius, weeklyItem.minTemp))

        Text(
            text = stringResource(R.string.sunset),
            fontWeight = FontWeight.Bold
        )
        Text(text = weeklyItem.sunset)

        Text(
            text = stringResource(R.string.sunrise),
            fontWeight = FontWeight.Bold
        )
        Text(text = weeklyItem.sunrise)

        Text(
            text = stringResource(R.string.windSpeed),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.windSpeedUnits, weeklyItem.windSpeed)
        )
    }

}
