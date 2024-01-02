package com.example.restaurantsearcher

import Photo
import PhotoSize
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

    val sampleList =
        listOf(
            Shop(
                id = "OOOOOOOOOO",
                name = "ラーメン嬉屋1",
                address = "大分県宇佐市大字下拝田709-5",
                lat = 33.556324,
                lng = 131.27592,
                access = "xx駅出口より徒歩約yy分",
                photo =
                    Photo(
                        pc =
                            PhotoSize(
                                l = "https://images.miil.me/j/8c057d32-ca3f-11ea-a5ac-06f1f1f9355e.orig.jpg",
                                m = "https://images.miil.me/j/bea01fb8-47c2-11ea-9eea-06c04e0bd7fe.orig.jpg",
                                s = "https://images.miil.me/j/9955d61c-ca3f-11ea-812b-06c832040a7e.orig.jpg",
                            ),
                        mobile =
                            PhotoSize(
                                l = "https://images.miil.me/j/8c057d32-ca3f-11ea-a5ac-06f1f1f9355e.orig.jpg",
                                m = "https://images.miil.me/j/bea01fb8-47c2-11ea-9eea-06c04e0bd7fe.orig.jpg",
                                s = "https://images.miil.me/j/9955d61c-ca3f-11ea-812b-06c832040a7e.orig.jpg",
                            ),
                    ),
                open = "月～日、祝日、祝前日: 11:00～20:00",
            ),
            Shop(
                id = "OOOOOOOOOO",
                name = "ラーメン嬉屋2",
                address = "大分県宇佐市大字下拝田709-5",
                lat = 33.556324,
                lng = 131.27592,
                access = "xx駅出口より徒歩約yy分",
                photo =
                    Photo(
                        pc =
                            PhotoSize(
                                l = "https://images.miil.me/j/8c057d32-ca3f-11ea-a5ac-06f1f1f9355e.orig.jpg",
                                m = "https://images.miil.me/j/bea01fb8-47c2-11ea-9eea-06c04e0bd7fe.orig.jpg",
                                s = "https://images.miil.me/j/9955d61c-ca3f-11ea-812b-06c832040a7e.orig.jpg",
                            ),
                        mobile =
                            PhotoSize(
                                l = "https://images.miil.me/j/8c057d32-ca3f-11ea-a5ac-06f1f1f9355e.orig.jpg",
                                m = "https://images.miil.me/j/bea01fb8-47c2-11ea-9eea-06c04e0bd7fe.orig.jpg",
                                s = "https://images.miil.me/j/9955d61c-ca3f-11ea-812b-06c832040a7e.orig.jpg",
                            ),
                    ),
                open = "月～日、祝日、祝前日: 11:00～20:00",
            ),
        )

    fun updateSearchResults(results: List<Shop>?) {
        viewModelScope.launch {
            _searchResults.update { currentResults ->
                currentResults.copy(shop = results ?: emptyList())
            }
            println("resultScreenShop: ${searchResults.value.shop}")
        }
    }
}
