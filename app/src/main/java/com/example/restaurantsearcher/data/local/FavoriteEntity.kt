package com.example.restaurantsearcher.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey @ColumnInfo(name = "favorite_shop") val itemId: String
)