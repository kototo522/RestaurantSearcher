package com.example.restaurantsearcher.ui.search.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.restaurantsearcher.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(extend: MutableState<Boolean>) {
    val navigationIcon = if (extend.value) Icons.Default.Close else Icons.Default.Search
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name), fontSize = 16.sp, textAlign = TextAlign.Center)
        },
        navigationIcon = {
            IconButton(onClick = { extend.value = !extend.value }) {
                Icon(imageVector = navigationIcon, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primaryContainer),
    )
}
