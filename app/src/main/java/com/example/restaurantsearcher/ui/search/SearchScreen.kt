package com.example.restaurantsearcher.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.restaurantsearcher.ui.search.component.SearchTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    val searchText = remember { mutableStateOf("") }
    var extend = remember { mutableStateOf(false) }
    Scaffold(
        topBar = { SearchTopBar(extend = extend, searchText = searchText) },
        content = {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(it),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = searchText.value)
            }
        },
    )
}
