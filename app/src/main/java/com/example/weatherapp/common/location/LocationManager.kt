package com.example.weatherapp.common.location

import android.location.Location

fun interface LocationManager {

    suspend fun getLocation(): Location?
}