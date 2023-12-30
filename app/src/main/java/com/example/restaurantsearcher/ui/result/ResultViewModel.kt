package com.example.restaurantsearcher.ui.result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.restaurantsearcher.ui.result.component.data.ResultItem

class ResultViewModel : ViewModel() {
    var searchResults by mutableStateOf(mutableStateListOf<ResultItem>())

    fun updateSearchResults(results: List<ResultItem>) {
        searchResults.clear()
        searchResults.addAll(results)
    }
}