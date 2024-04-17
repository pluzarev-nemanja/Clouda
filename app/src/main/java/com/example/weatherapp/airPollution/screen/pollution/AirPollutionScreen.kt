package com.example.weatherapp.airPollution.screen.pollution

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.domain.model.PastAirPollution
import com.example.weatherapp.R
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.components.LoadingScreen
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AirPollutionScreen(
    paddingValues: PaddingValues,
    airPollutionUIState: AirPollutionUIState,
    formatDate: (Long) -> String
) {


    when (airPollutionUIState) {

        AirPollutionUIState.Loading -> LoadingScreen()
        AirPollutionUIState.Empty -> {}
        is AirPollutionUIState.Error -> {}
        is AirPollutionUIState.Success -> PastAirPollutionScreen(
            pastAirPollutionList = airPollutionUIState.data,
            paddingValues = paddingValues,
            formatDate = formatDate
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PastAirPollutionScreen(
    pastAirPollutionList: List<PastAirPollution>,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    formatDate: (Long) -> String
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.smallPadding))
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        Header(
            title = stringResource(id = R.string.airPollution),
            icon = painterResource(id = R.drawable.ic_pollution),
            stringResource(R.string.pastFiveDaysData)
        )
        AirPollutionContent(
            pastAirPollutionList = pastAirPollutionList,
            formatDate = formatDate
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AirPollutionContent(
    pastAirPollutionList: List<PastAirPollution>,
    modifier: Modifier = Modifier,
    formatDate: (Long) -> String
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

        items(pastAirPollutionList) { pastAirPollution: PastAirPollution ->
            AirPollutionItem(
                pastAirPollution = pastAirPollution,
                formatDate = formatDate
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AirPollutionItem(
    pastAirPollution: PastAirPollution,
    formatDate: (Long) -> String
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
            text = formatDate.invoke(pastAirPollution.time),
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
                Text(text = stringResource(R.string.co, pastAirPollution.co))
                Text(text = stringResource(R.string.nh3, pastAirPollution.nh3))
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = stringResource(R.string.no, pastAirPollution.no))
                Text(text = stringResource(R.string.o3, pastAirPollution.o3))
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = stringResource(R.string.pm10, pastAirPollution.pm10))
                Text(text = stringResource(R.string.no2, pastAirPollution.no2))
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {

                Text(text = stringResource(R.string.pm25, pastAirPollution.pm25))
                Text(text = stringResource(R.string.so2, pastAirPollution.so2))
            }
        }
    }


}