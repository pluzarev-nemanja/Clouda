package com.example.weatherapp.common.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class LocationManagerImpl(private val context: Context) : LocationManager {

    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    private val cancellationTokenSource = CancellationTokenSource()

    override suspend fun getLocation(): Location? {
        return if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            fusedLocationProvider = LocationServices.getFusedLocationProviderClient(context)

            fusedLocationProvider.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener { location: Location? ->
                Timber.d("LocationManager : ${location?.longitude} ${location?.longitude}")
            }.addOnFailureListener{
                Timber.e(it, "LocationManger failed to init. ")
            }.await()

        } else null
    }

}