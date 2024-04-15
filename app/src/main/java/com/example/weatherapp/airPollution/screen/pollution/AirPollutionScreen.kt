package com.example.weatherapp.airPollution.screen.pollution

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.domain.model.PastAirPollution
import com.example.weatherapp.R
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.components.LoadingScreen

@Composable
fun AirPollutionScreen(
    paddingValues: PaddingValues,
    airPollutionUIState: AirPollutionUIState
) {


    when (airPollutionUIState) {

        AirPollutionUIState.Loading -> LoadingScreen()
        AirPollutionUIState.Empty -> {}
        is AirPollutionUIState.Error -> {}
        is AirPollutionUIState.Success -> PastAirPollutionScreen(
            pastAirPollutionList = airPollutionUIState.data,
            paddingValues = paddingValues
        )
    }
}


@Composable
fun PastAirPollutionScreen(
    pastAirPollutionList: List<PastAirPollution>,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.smallPadding))
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        Header(
            title = "Air pollution",
            icon = painterResource(id = R.drawable.ic_pollution),
            "past 5 days data"
        )
        AirPollutionContent(
            pastAirPollutionList = pastAirPollutionList
        )
    }


}

@Composable
fun Header(
    title: String,
    icon: Painter,
    smallText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.largePadding)),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.smallPadding))
                    .size(dimensionResource(id = R.dimen.iconSize)),
                painter = icon, contentDescription = title
            )
        }
        Text(
            text = smallText,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Light
        )

    }


}

@Composable
fun AirPollutionContent(
    pastAirPollutionList: List<PastAirPollution>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

        items(pastAirPollutionList) { pastAirPollution: PastAirPollution ->
            AirPollutionItem(
                pastAirPollution = pastAirPollution
            )
        }
    }

}

@Composable
fun AirPollutionItem(
    pastAirPollution: PastAirPollution
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.mediumPadding)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.smallPadding)),
            text = "MONDAY",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)

            ) {
                Text(text = "CO: ${pastAirPollution.co}")
                Text(text = "NH3: ${pastAirPollution.nh3}")
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = "NO: ${pastAirPollution.no}")
                Text(text = "O3: ${pastAirPollution.o3}")
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = "PM10: ${pastAirPollution.pm10}")
                Text(text = "NO2: ${pastAirPollution.no2}")
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = "PM25: ${pastAirPollution.pm25}")
                Text(text = "SO2: ${pastAirPollution.so2}")
            }
        }
    }


}