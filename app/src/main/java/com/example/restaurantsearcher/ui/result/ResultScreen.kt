package com.example.restaurantsearcher.ui.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.restaurantsearcher.ui.result.component.ResultListItem
import com.example.restaurantsearcher.ui.result.component.data.ResultItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavController) {
    val imageTitleList =
        listOf(
            ResultItem("shopName", "Access", "sampleImage"),
            ResultItem("shopName", "Access", "sampleImage"),
            ResultItem("shopName", "Access", "sampleImage"),
        )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "検索結果") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "戻る")
                    }
                },
            )
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                items(imageTitleList) { item ->
                    ResultListItem(item = item)
                    Divider()
                }
            }
        }
    }
}
