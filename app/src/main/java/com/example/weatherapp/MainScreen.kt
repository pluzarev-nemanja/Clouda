package com.example.weatherapp

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.navigation.BottomNavigationBar
import com.example.weatherapp.common.navigation.graph.NavigationGraph
import com.example.weatherapp.common.navigation.routes.BottomNavItem
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import timber.log.Timber

@Composable
fun MainScreen(
    navController: NavHostController,
    dailyWeatherUIState: DailyWeatherUIState,
    airPollutionUIState: AirPollutionUIState,
    formatDate: (Long) -> String
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
            formatDate = formatDate,
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
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.primary)
    )
}