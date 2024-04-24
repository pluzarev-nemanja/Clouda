package com.example.weatherapp.dailyWeather.screen.home

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.common.components.ErrorScreen
import com.example.weatherapp.common.components.LoadingScreen
import com.example.weatherapp.dailyWeather.model.DailyWeatherUIModel
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState

@Composable
fun HomeScreen(
    dailyWeatherUIState: DailyWeatherUIState,
    paddingValues: PaddingValues,
    onNavigate: () -> Unit
) {


    when (dailyWeatherUIState) {
        DailyWeatherUIState.Loading -> LoadingScreen()

        is DailyWeatherUIState.Success -> CurrentWeatherScreen(
            dailyWeather = dailyWeatherUIState.data,
            paddingValues = paddingValues,
            onNavigate = onNavigate
        )

        is DailyWeatherUIState.Error -> {
            when (dailyWeatherUIState) {
                is DailyWeatherUIState.Error.Unknown -> ErrorScreen(message = dailyWeatherUIState.message)
                is DailyWeatherUIState.Error.Internet -> ErrorScreen(message = dailyWeatherUIState.message)
                is DailyWeatherUIState.Error.Server -> ErrorScreen(message = dailyWeatherUIState.message)
            }
        }

        DailyWeatherUIState.Empty -> {

        }

    }

}

@SuppressLint("NewApi")
@Composable
fun CurrentWeatherScreen(
    dailyWeather: DailyWeatherUIModel,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onNavigate: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.LightGray,
                        MaterialTheme.colorScheme.primaryContainer
                    )
                )
            )
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.mediumPadding)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = dailyWeather.icon,
                contentDescription = dailyWeather.detailDescription,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.LargeIconSize))
                    .weight(1f)
            )
            Text(
                text = dailyWeather.currentTime,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.largePadding)))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        id = R.string.celsius,
                        dailyWeather.currentTemp
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.extraLargeFontSize).value.sp
                )
                Text(
                    text = dailyWeather.location,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.mediumFontSize).value.sp
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.extraLargePadding)))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherDetailsItem(
                    icon = R.drawable.ic_sun,
                    bigText = stringResource(
                        R.string.feelsLike
                    ),
                    smallText = stringResource(id = R.string.celsius, dailyWeather.feelsLikeTemp)
                )
                Divider(
                    color = MaterialTheme.colorScheme.inversePrimary, modifier = Modifier
                        .height(
                            dimensionResource(id = R.dimen.largeHeight)
                        )
                        .width(1.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pollution),
                        contentDescription = stringResource(R.string.airPollution),

                        modifier = Modifier.size(dimensionResource(id = R.dimen.iconSize))
                    )
                    Button(
                        onClick = {
                            onNavigate.invoke()
                        },
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.smallPadding))
                    ) {
                        Text(text = stringResource(R.string.checkAirPollution))
                    }
                }
                Divider(
                    color = MaterialTheme.colorScheme.inversePrimary, modifier = Modifier
                        .height(
                            dimensionResource(id = R.dimen.largeHeight)
                        )
                        .width(1.dp)
                )
                WeatherDetailsItem(
                    icon = R.drawable.ic_wind,
                    bigText = dailyWeather.detailDescription,
                    smallText = ""
                )
            }


        }

    }

}


@Composable
fun WeatherDetailsItem(
    @DrawableRes icon: Int,
    bigText: String,
    smallText: String
) {

    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.smallPadding)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = bigText,

            modifier = Modifier.size(dimensionResource(id = R.dimen.iconSize))
        )
        Text(text = bigText)
        Text(text = smallText)
    }


}