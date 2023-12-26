package com.example.restaurantsearcher.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.ListItemPicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchExpandableBar(
    extend: MutableState<Boolean>,
    navController: NavController,
    paddingValues: PaddingValues,
) {
    val searchText = remember { mutableStateOf("") }
    val radiusValues = listOf(300, 500, 1000, 2000, 3000)
    var state by remember { mutableStateOf(radiusValues[0]) }

    Box(
        modifier =
            Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "検索", fontSize = 12.sp)
            TextField(
                value = searchText.value,
                onValueChange = { value -> searchText.value = value },
                label = { Text(text = "Search") },
                colors =
                    TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(20.dp),
            )
            Text(text = "絞り込み(半径)", fontSize = 12.sp)
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Box(
                    modifier =
                        Modifier
                            .align(Alignment.CenterVertically)
                            .width(100.dp)
                            .height(120.dp),
                ) {
                    ListItemPicker(
                        label = { value -> value.toString() },
                        value = state,
                        onValueChange = { value -> state = value },
                        list = radiusValues,
                        modifier = Modifier.width(80.dp),
                    )
                }
                Text(text = "m", fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterVertically))
            }
            Button(onClick = {
                extend.value = false
                navController.navigate("result")
            }, modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp)) {
                Text(text = "検索する")
            }
        }
    }
}
