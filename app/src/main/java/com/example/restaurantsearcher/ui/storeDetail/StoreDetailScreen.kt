package com.example.restaurantsearcher.ui.storeDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.restaurantsearcher.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreDetailScreen(
    navController: NavController,
    appViewModel: AppViewModel,
) {
//    val storeDetailViewModel = StoreDetailViewModel()
    val item = appViewModel.selectedItem.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "店舗詳細", fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "戻る")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primaryContainer),
            )
        },
    ) {
        if (item != null)
            {
                Column(modifier = Modifier.padding(it)) {
                    AsyncImage(
                        model = item.value!!.photo.mobile.l,
                        contentDescription = null,
                        modifier = Modifier.weight(4f),
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier =
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 24.dp),
                    ) {
                        Text(
                            text = item.value!!.name,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = "住所",
                                modifier = Modifier.padding(end = 8.dp),
                            )
                            Text(
                                text = item.value!!.address,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "営業時間",
                                modifier = Modifier.padding(end = 8.dp),
                            )
                            Text(
                                text = item.value!!.open,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }
                    }
                }
            } else {
            Text(text = "アクセスに失敗しました")
        }
    }
}
