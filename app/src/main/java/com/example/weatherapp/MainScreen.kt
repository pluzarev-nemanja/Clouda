package com.example.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.navigation.BottomNavigationBar
import com.example.weatherapp.common.navigation.graph.NavigationGraph
import com.example.weatherapp.common.navigation.routes.BottomNavItem
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import com.example.weatherapp.weeklyWeather.uiState.WeeklyWeatherUIState

@Composable
fun MainScreen(
    navController: NavHostController,
    dailyWeatherUIState: DailyWeatherUIState,
    airPollutionUIState: AirPollutionUIState,
    weeklyWeatherUIState: WeeklyWeatherUIState,
    isInDarkTheme: Boolean,
    onDarkThemeSwitch: () -> Unit,
    onRetryClick: () -> Unit
) {

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            MyAppBar()
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                index = selectedIndex
            )
        }
    ) { paddingValues ->

        NavigationGraph(
            navController = navController,
            dailyWeatherUIState = dailyWeatherUIState,
            paddingValues = paddingValues,
            airPollutionUIState = airPollutionUIState,
            weeklyWeatherUIState = weeklyWeatherUIState,
            onRetryClick = onRetryClick,
            isInDarkTheme = isInDarkTheme,
            onDarkThemeSwitch = onDarkThemeSwitch,
            onNavigate = {
                navController.navigate(BottomNavItem.AirPollution.route)
                selectedIndex = 2
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar() {

    CenterAlignedTopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.ExtraBold
                )
                AsyncImage(
                    model = R.drawable.ic_launcher_foreground, contentDescription = "icon",
                    modifier = Modifier.size(dimensionResource(id = R.dimen.topBarIconSize))
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.primary)
    )
}