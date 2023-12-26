package com.example.restaurantsearcher.ui.result.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantsearcher.ui.result.component.data.ResultItem

@Composable
fun ResultListItem(item: ResultItem) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = item.shopImage,
            contentDescription = null,
            modifier =
                Modifier
                    .size(72.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(36.dp))
        Column {
            Text(
                text = item.shopName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp, end = 16.dp),
            )
            Text(
                text = item.address,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(end = 16.dp),
            )
            Text(
                text = item.access,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(end = 16.dp),
            )
        }
    }
}
