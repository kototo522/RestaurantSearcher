package com.example.restaurantsearcher.ui.result

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restaurantsearcher.AppViewModel
import com.example.restaurantsearcher.ui.result.component.ResultListItem
import com.example.restaurantsearcher.ui.result.component.ResultViewModel
import com.example.restaurantsearcher.ui.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    navController: NavController,
    appViewModel: AppViewModel,
) {
    val resultViewModel = ResultViewModel(appViewModel)
    val shopList by appViewModel.searchResults.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "検索結果", fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "戻る")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primaryContainer),
            )
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                if (shopList.shop.isNotEmpty()) {
                    println("resultScreenShopList: success")
                    items(shopList.shop) { item ->
                        Column(
                            modifier =
                                Modifier.clickable {
                                    resultViewModel.selectItem(item)
                                    navController.navigate("storeDetail")
                                },
                        ) {
                            ResultListItem(item = item)
                            Divider(modifier = Modifier.padding(horizontal = 28.dp))
                        }
                    }
                } else {
                    println("resultScreenShopList: $shopList")
                    item {
                        Text("結果がありません", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
