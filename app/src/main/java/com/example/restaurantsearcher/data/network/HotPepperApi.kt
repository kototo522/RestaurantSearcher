package com.example.restaurantsearcher.data.network

import com.example.restaurantsearcher.BuildConfig
import com.example.restaurantsearcher.ui.result.component.data.ResultItem
import com.google.android.gms.maps.model.LatLng
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun HotPepperApi(
    location: LatLng,
    searchText: String,
    selectedValue: String,
): List<ResultItem> {
    val apiKey = BuildConfig.HOT_PEPPER_API_KEY
    val baseUrl = "http://webservice.recruit.co.jp/hotpepper/"
    val range =
        when (selectedValue) {
            "300" -> 1
            "500" -> 2
            "1000" -> 3
            "2000" -> 4
            else -> 5
        }

    try {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        val retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
                .build()

        val service = retrofit.create(HotPepperApiService::class.java)

        val response =
            runBlocking {
                service.getRestaurants(apiKey, location.latitude, location.longitude, searchText, range, "json")
            }

        response.results.forEach {
            println("店名: ${it.shopName}, 住所: ${it.address}")
        }
        return response.results
    } catch (e: Exception) {
        println("Error: ${e.localizedMessage}")
        return emptyList()
    }
}
