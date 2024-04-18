package com.example.weatherapp.airPollution.screen.pollution

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            paddingValues = paddingValues
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
            .padding(bottom = dimensionResource(id = R.dimen.smallPadding)),
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
    paddingValues: PaddingValues
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
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
            .padding(bottom = dimensionResource(id = R.dimen.smallPadding)),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = airPollution.date,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PollutionDataItem(bigText = "NO", data = airPollution.nitrogenMonoxide)
                PollutionDataItem(bigText = "NO2", data = airPollution.nitrogenDioxide)
                PollutionDataItem(bigText = "NH3", data = airPollution.ammonia)
                PollutionDataItem(bigText = "O3", data = airPollution.ozone)

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PollutionDataItem(bigText = "NO", data = airPollution.nitrogenMonoxide)
                PollutionDataItem(bigText = "NO2", data = airPollution.nitrogenDioxide)
                PollutionDataItem(bigText = "NH3", data = airPollution.ammonia)
                PollutionDataItem(bigText = "O3", data = airPollution.ozone)

            }
        }
    }

}

@Composable
fun PollutionDataItem(
    bigText: String,
    data: String
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.smallPadding))
    ) {

        Column(
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.itemPadding)
                )
                .size(dimensionResource(id = R.dimen.extraLargePadding))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.iconSize)))
                .background(MaterialTheme.colorScheme.inversePrimary),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = bigText,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = data,
                color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
            )
        }

        AsyncImage(
            modifier = Modifier.size(dimensionResource(id = R.dimen.largeHeight)),
            model = R.drawable.ic_cloud, contentDescription = stringResource(id = R.string.cloudIcon))
    }

}