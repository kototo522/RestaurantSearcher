package com.example.restaurantsearcher.ui.search

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsearcher.data.network.HotPepperApiRepository
import com.example.restaurantsearcher.ui.result.ResultViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class SearchViewModel : ViewModel() {
    private val resultViewModel = ResultViewModel()

    fun performSearch(
        location: LatLng,
        searchText: String,
        selectedRadius: String,
    ) {
        viewModelScope.launch {
            try {
                val response = HotPepperApiRepository.instance.getGourmetShop(location, searchText, selectedRadius)
                val shopList = response.body()?.results?.shop
                resultViewModel.updateSearchResults(shopList)
            } catch (e: Exception) {
                println("SearchViewModel: Exception - ${e.message}")
            }
        }
    }
}
