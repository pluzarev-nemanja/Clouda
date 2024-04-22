package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.airPollution.viewModel.AirPollutionViewModel
import com.example.weatherapp.common.components.SinglePermissionDialog
import com.example.weatherapp.common.model.LatLong
import com.example.weatherapp.common.ui.theme.WeatherAppTheme
import com.example.weatherapp.dailyWeather.viewModel.HomeViewModel
import com.example.weatherapp.weeklyWeather.viewModel.WeeklyWeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class MainActivity : ComponentActivity() {


    private val airPollutionViewModel by viewModel<AirPollutionViewModel>()
    private val weeklyWeatherViewModel by viewModel<WeeklyWeatherViewModel>()
    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    private val cancellationTokenSource = CancellationTokenSource()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {

                var lat by remember {
                    mutableDoubleStateOf(0.0)
                }
                var long by remember {
                    mutableDoubleStateOf(0.0)
                }
                var homeViewModel by remember { mutableStateOf<HomeViewModel?>(null) }


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    SinglePermissionDialog(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {

                        fusedLocationProvider =
                            LocationServices.getFusedLocationProviderClient(this)

                        LaunchedEffect(key1 = true) {

                            fusedLocationProvider.getCurrentLocation(
                                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                                cancellationTokenSource.token
                            ).addOnSuccessListener { location: Location ->
                                lat = location.latitude
                                long = location.longitude
                            }.addOnFailureListener { exception: Exception ->
                                Timber.d(exception)
                            }.await()

                            homeViewModel = getViewModel(parameters = { parametersOf(LatLong(lat, long)) })

                        }

                        if (homeViewModel != null) {


                            val dailyWeatherUIState =
                                homeViewModel!!.dailyWeatherUIState.collectAsStateWithLifecycle().value
                            val airPollutionUIState =
                                airPollutionViewModel.airPollutionUIState.collectAsStateWithLifecycle().value
                            val weeklyWeatherUIState =
                                weeklyWeatherViewModel.weeklyWeatherUIState.collectAsStateWithLifecycle().value


                            MainScreen(
                                navController = rememberNavController(),
                                dailyWeatherUIState = dailyWeatherUIState,
                                airPollutionUIState = airPollutionUIState,
                                weeklyWeatherUIState = weeklyWeatherUIState
                            )


                        }


                    }


                }

            }
        }
    }

    override fun onStop() {
        super.onStop()
        cancellationTokenSource.cancel()
    }
}

