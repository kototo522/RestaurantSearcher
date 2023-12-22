@file:Suppress("DEPRECATION")

package com.example.restaurantsearcher.ui.search.component

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

object LocationUtils {
    @SuppressLint("MissingPermission")
    fun requestLocationResultCallback(
        fusedLocationProviderClient: FusedLocationProviderClient,
        locationResultCallback: (LocationResult) -> Unit,
    ) {
        val locationCallback =
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)

                    locationResultCallback(locationResult)
                    fusedLocationProviderClient.removeLocationUpdates(this)
                }
            }

        val locationRequest = LocationRequest.create().apply { Priority.PRIORITY_HIGH_ACCURACY }
        Looper.myLooper()?.let { looper ->
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                looper,
            )
        }
    }

    @Composable
    fun LocationPermissionsDialog(
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit,
    ) {
        val requestLocationPermissionLauncher =
            rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission(),
            ) { isGranted: Boolean ->
                if (isGranted) {
                    onPermissionGranted()
                } else {
                    onPermissionDenied()
                }
            }
        SideEffect {
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @Composable
    fun LocationSettingDialog(
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    ) {
        val context: Context = LocalContext.current
        val enableLocationSettingLauncher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
            ) { activityResult ->
                if (activityResult.resultCode == Activity.RESULT_OK) {
                    onSuccess()
                } else {
                    onFailure()
                }
            }
        val locationRequest =
            LocationRequest.create().apply {
                Priority.PRIORITY_HIGH_ACCURACY
            }
        val locationRequestBuilder =
            LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
        val locationSettingsResponseTask =
            LocationServices.getSettingsClient(context)
                .checkLocationSettings(locationRequestBuilder.build())
        locationSettingsResponseTask.addOnSuccessListener {
            onSuccess()
        }
        locationSettingsResponseTask.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(exception.resolution).build()
                    enableLocationSettingLauncher.launch(intentSenderRequest)
                } catch (sendEx: IntentSender.SendIntentException) {
                    sendEx.printStackTrace()
                }
            } else {
                onFailure()
            }
        }
    }

    fun isLocationPermissionGranted(context: Context): Boolean {
        return (
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
}
