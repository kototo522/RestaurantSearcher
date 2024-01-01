package com.example.restaurantsearcher.ui.result

import Shop
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {
    var searchResults by mutableStateOf(mutableStateListOf<Shop>())


    fun updateSearchResults(results: List<Shop>?) {
        searchResults.clear()
        results?.let {
            searchResults.addAll(it)
            println("resultScreenShop: ${searchResults.get(0)}")
        }
    }
}
