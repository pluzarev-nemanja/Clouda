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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.domain.model.DailyWeather
import com.example.weatherapp.R
import com.example.weatherapp.common.components.LoadingScreen
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

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

        }

        DailyWeatherUIState.Empty -> {

        }

    }

}

@SuppressLint("NewApi")
@Composable
fun CurrentWeatherScreen(
    dailyWeather: DailyWeather,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onNavigate: () -> Unit
) {
    var formatted by remember {
        mutableStateOf("")
    }
    val current = remember {
        LocalDateTime.now()
    }
    val formatter = remember {
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    }
    LaunchedEffect(key1 = dailyWeather) {
        formatted = current.format(formatter)
    }


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
            .padding(top = paddingValues.calculateTopPadding()),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.mediumPadding)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = dailyWeather.icon,
                contentDescription = dailyWeather.weatherDescription,
                modifier = Modifier.size(dimensionResource(id = R.dimen.LargeIconSize))
            )
            Text(
                text = formatted,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.largePadding)))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        id = R.string.celsius,
                        dailyWeather.currentTemp.toInt().toString()
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
                    .padding(dimensionResource(id = R.dimen.smallPadding)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherDetailsItem(
                    icon = R.drawable.ic_sun,
                    text = stringResource(R.string.feelsLike,dailyWeather.feelsLike.roundToInt().toString())
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

                        modifier = Modifier.size(dimensionResource(id = R.dimen.mediumIconSize))
                    )
                    Button(onClick = {
                        onNavigate.invoke()
                    }) {
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
                    text = dailyWeather.weatherDescription
                )
            }


        }

    }

}


@Composable
fun WeatherDetailsItem(
    @DrawableRes icon: Int,
    text: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,

            modifier = Modifier.size(dimensionResource(id = R.dimen.mediumIconSize))
        )
        Text(text = text)
    }


}