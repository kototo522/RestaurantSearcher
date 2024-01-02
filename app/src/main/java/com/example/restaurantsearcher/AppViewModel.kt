package com.example.restaurantsearcher

import Results
import Shop
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow(Results(emptyList()))
    val searchResults: StateFlow<Results> = _searchResults.asStateFlow()

    private val _selectedItem = MutableStateFlow<Shop?>(null)
    val selectedItem: StateFlow<Shop?> = _selectedItem.asStateFlow()

    fun updateSearchResults(results: List<Shop>?) {
        viewModelScope.launch {
            _searchResults.update { currentResults ->
                currentResults.copy(shop = results ?: emptyList())
            }
        }
    }

    fun displayStoreDetail(results: Shop?) {
        viewModelScope.launch {
            _selectedItem.value =
                results?.let { shop ->
                    shop.copy(id = shop.id, name = shop.name, address = shop.address, lat = shop.lat, lng = shop.lng, open = shop.open)
                }
        }
    }
}
