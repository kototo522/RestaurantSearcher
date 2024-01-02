package com.example.restaurantsearcher.data.network

import com.example.restaurantsearcher.BuildConfig
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HotPepperApiRepository {
    private val apiKey = BuildConfig.HOT_PEPPER_API_KEY

    private val gson: Gson =
        GsonBuilder()
            .setLenient()
            .create()

    private val retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://webservice.recruit.co.jp/hotpepper/")
            .build()

    private val service =
        retrofit.create(HotPepperApiService::class.java)

    // シングルトン
    companion object Factory {
        val instance: HotPepperApiRepository
            @Synchronized get() {
                return HotPepperApiRepository()
            }
    }

    // ショップデータの取得
    suspend fun getGourmetShop(
        location: LatLng,
        searchText: String,
        selectedValue: String,
    ) = service.getRestaurants(
        apiKey,
        location.latitude,
        location.longitude,
        searchText,
        when (selectedValue) {
            "300" -> 1
            "500" -> 2
            "1000" -> 3
            "2000" -> 4
            else -> 5
        },
        "json",
    )
}
