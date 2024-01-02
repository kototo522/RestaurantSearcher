package com.example.restaurantsearcher.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantsearcher.ui.result.ResultScreen
import com.example.restaurantsearcher.ui.search.SearchScreen
import com.example.restaurantsearcher.ui.storeDetail.StoreDetailScreen
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun Navigation(fusedLocationProviderClient: FusedLocationProviderClient) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(fusedLocationProviderClient = fusedLocationProviderClient, navController = navController)
        }
        composable("result") {
            ResultScreen(navController)
        }
        composable("storeDetail") {
            StoreDetailScreen(navController)
        }
    }
}
