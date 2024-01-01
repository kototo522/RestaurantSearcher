package com.example.restaurantsearcher.ui.result

import Results
import Shop
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResultViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow(Results(emptyList()))
    val searchResults: StateFlow<Results> = _searchResults.asStateFlow()

    fun updateSearchResults(results: List<Shop>?) {
        viewModelScope.launch {
            _searchResults.update { currentResults ->
                currentResults.copy(shop = results ?: emptyList())
            }
            println("resultScreenShop: ${searchResults.value.shop}")
        }
    }
}
