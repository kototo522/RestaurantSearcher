package com.example.restaurantsearcher.ui.storeDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreDetailScreen(navController: NavController) {
    val storeDetailViewModel = StoreDetailViewModel()
    val item = storeDetailViewModel.sampleShop
    var isStarred by remember { mutableStateOf(false) }
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
                        model = item.photo.mobile.l,
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
                        Row(
                            modifier = Modifier.padding(8.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = item.name,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            )
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "お気に入り",
                                modifier = Modifier.size(32.dp).clickable { isStarred = !isStarred },
                                tint = if (isStarred) Color.Yellow else Color.LightGray,
                            )
                        }
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = "住所",
                                modifier = Modifier.padding(end = 8.dp),
                            )
                            Text(
                                text = item.address,
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
                                text = item.open,
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
