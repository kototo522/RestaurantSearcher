package com.example.restaurantsearcher.data.local

import Shop
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    val item: Shop,
)