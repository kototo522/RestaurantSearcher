package com.example.restaurantsearcher.ui.result

import Shop
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restaurantsearcher.ui.result.component.ResultListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavController) {
    val resultViewModel = viewModel<ResultViewModel>()
    var resultList by remember { mutableStateOf(mutableStateListOf<Shop>()) }

    LaunchedEffect(resultViewModel) {
        launch(Dispatchers.IO) {
            resultList = resultViewModel.searchResults
            println("shop${resultViewModel.searchResults.toList()}")
        }
    }

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
                if (resultList.isNotEmpty()) {
                    println("resultScreenShopList: success")
                    items(resultList) { item ->
                        ResultListItem(item = item)
                        Divider(modifier = Modifier.padding(horizontal = 40.dp))
                    }
                } else {
                    println("resultScreenShopList: ${resultList}")
                    item {
                        Text("結果がありません", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
