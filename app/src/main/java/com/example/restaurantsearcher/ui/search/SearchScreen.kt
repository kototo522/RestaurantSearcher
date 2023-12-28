package com.example.restaurantsearcher.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.restaurantsearcher.data.network.HotPepperApi
import com.example.restaurantsearcher.ui.search.component.LocationPermissionsDialogs
import com.example.restaurantsearcher.ui.search.component.LocationUtils
import com.example.restaurantsearcher.ui.search.component.SearchExpandableBar
import com.example.restaurantsearcher.ui.search.component.SearchTopBar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    fusedLocationProviderClient: FusedLocationProviderClient,
    navController: NavController,
) {
    val extend = remember { mutableStateOf(false) }
    var location by remember { mutableStateOf(LatLng(1.35, 103.87)) }
    var requestLocationUpdate by remember { mutableStateOf(true) }
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(location, 14f)
        }

    Scaffold(
        topBar = { SearchTopBar(extend = extend) },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it),
            verticalArrangement = Arrangement.Center,
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true),
            )
        }
        if (requestLocationUpdate) {
            LocationPermissionsDialogs(
                updateCurrentLocation = {
                    requestLocationUpdate = false
                    LocationUtils.requestLocationResultCallback(fusedLocationProviderClient) { locationResult ->
                        locationResult.lastLocation?.let { currentLocation ->
                            location = LatLng(currentLocation.latitude, currentLocation.longitude)
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 14f)
                        }
                    }
                },
            )
        } else {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 14f)
        }

        if (extend.value) {
            SearchExpandableBar(
                extend,
                onSearchClick = { newSearchText, newSelectedRadius ->
                    HotPepperApi(location, newSearchText, newSelectedRadius)
                    navController.navigate("result")
                },
                it,
            )
        }
    }
}
