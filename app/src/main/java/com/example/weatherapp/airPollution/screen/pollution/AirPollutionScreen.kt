package com.example.weatherapp.airPollution.screen.pollution

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.weatherapp.R
import com.example.weatherapp.airPollution.model.AirPollutionUIModel
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.components.LoadingScreen

@Composable
fun AirPollutionScreen(
    paddingValues: PaddingValues,
    airPollutionUIState: AirPollutionUIState,
) {


    when (airPollutionUIState) {

        AirPollutionUIState.Loading -> LoadingScreen()
        AirPollutionUIState.Empty -> {}
        is AirPollutionUIState.Error -> {}
        is AirPollutionUIState.Success -> PastAirPollutionScreen(
            airPollutionList = airPollutionUIState.data,
            paddingValues = paddingValues,
        )
    }
}


@Composable
fun PastAirPollutionScreen(
    airPollutionList: List<AirPollutionUIModel>,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.smallPadding))
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        Header(
            title = stringResource(id = R.string.airPollution),
            smallText = stringResource(R.string.pastWeek)
        )
        AirPollutionContent(
            airPollutionList = airPollutionList,
        )
    }


}

@Composable
fun Header(
    title: String,
    smallText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.largePadding)),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = smallText,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Light
        )

    }


}

@Composable
fun AirPollutionContent(
    airPollutionList: List<AirPollutionUIModel>,
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

        items(airPollutionList) { airPollution: AirPollutionUIModel ->
            AirPollutionItem(
                airPollution = airPollution,
            )
        }
    }

}

@Composable
fun AirPollutionItem(
    airPollution: AirPollutionUIModel,
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
            text = airPollution.date,
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
                Text(text = stringResource(R.string.co, airPollution.carbonMonoxide))
                Text(text = stringResource(R.string.nh3, airPollution.ammonia))
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = stringResource(R.string.no, airPollution.nitrogenMonoxide))
                Text(text = stringResource(R.string.o3, airPollution.ozone))
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = stringResource(R.string.pm10, airPollution.pm10))
                Text(text = stringResource(R.string.no2, airPollution.nitrogenDioxide))
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = stringResource(R.string.pm25, airPollution.pm25))
                Text(text = stringResource(R.string.so2, airPollution.sulphur))
            }
        }
    }


}