package com.example.restaurantsearcher.ui.search.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.restaurantsearcher.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    extend: MutableState<Boolean>,
    searchText: MutableState<String>,
) {
    TopAppBar(
        title = {
            if (extend.value) {
                TextField(
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    label = { Text(text = "Search") },
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Text(text = stringResource(id = R.string.app_name))
            }
        },
        navigationIcon = {
            IconButton(onClick = { extend.value = !extend.value }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        },
    )
}
