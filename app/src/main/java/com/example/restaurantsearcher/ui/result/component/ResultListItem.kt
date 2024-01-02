package com.example.restaurantsearcher.ui.result.component

import Shop
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

@Composable
fun ResultListItem(item: Shop) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = item.photo.mobile.l,
            contentDescription = null,
            modifier =
                Modifier
                    .size(84.dp)
                    .clip(shape = MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(24.dp))
        Column {
            Text(
                text = item.name,
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
