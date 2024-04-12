package com.example.weatherapp

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.weatherapp.common.navigation.BottomNavigationBar
import com.example.weatherapp.common.navigation.graph.NavigationGraph
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState

@Composable
fun MainScreen(
    navController: NavHostController,
    dailyWeatherUIState: DailyWeatherUIState
) {

    Scaffold(
        topBar = {
            MyAppBar()
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) { paddingValues ->

        NavigationGraph(
            navController = navController,
            dailyWeatherUIState = dailyWeatherUIState,
            paddingValues = paddingValues
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