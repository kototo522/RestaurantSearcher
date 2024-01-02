package com.example.restaurantsearcher.ui.result.component

import Shop
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsearcher.AppViewModel
import kotlinx.coroutines.launch

class ResultViewModel(private val appViewModel: AppViewModel) : ViewModel() {
    fun selectItem(shop: Shop) {
        viewModelScope.launch {
            try {
                appViewModel.displayStoreDetail(shop)
            } catch (e: Exception) {
                println("SearchViewModel: Exception - ${e.message}")
            }
        }
    }
}
