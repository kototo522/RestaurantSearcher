package com.example.restaurantsearcher.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}