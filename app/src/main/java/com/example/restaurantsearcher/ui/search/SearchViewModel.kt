package com.example.restaurantsearcher.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsearcher.data.network.HotPepperApi
import com.example.restaurantsearcher.ui.result.ResultViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val resultViewModel: ResultViewModel by lazy {
        ResultViewModel()
    }

    fun performSearch(
        location: LatLng,
        searchText: String,
        selectedRadius: String,
    ) {
        viewModelScope.launch {
            val results = HotPepperApi(location, searchText, selectedRadius)
            resultViewModel.updateSearchResults(results)
        }
    }
}
