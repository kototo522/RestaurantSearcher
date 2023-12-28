package com.example.restaurantsearcher.data.network

import com.example.restaurantsearcher.BuildConfig
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun HotPepperApi() {
    val apiKey = BuildConfig.HOT_PEPPER_API_KEY
    val baseUrl = "http://webservice.recruit.co.jp/hotpepper/"

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
                service.getRestaurants(apiKey, 35.67, 139.76, 1, 4)
            }

        // レスポンスの処理
        response.results.forEach {
            println("店名: ${it.shopName}, 住所: ${it.address}")
        }
    } catch (e: Exception) {
        println("Error: ${e.localizedMessage}")
    }
}
