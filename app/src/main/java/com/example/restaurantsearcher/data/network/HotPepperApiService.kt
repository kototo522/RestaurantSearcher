package com.example.restaurantsearcher.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface HotPepperApiService {
    @GET("gourmet/v1/")
    suspend fun getRestaurants(
        @Query("key") apiKey: String,
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("range") range: Int,
        @Query("order") order: Int,
    ): HotPepperApiResponse
}
