package com.example.restaurantsearcher

import android.app.Application
import com.example.restaurantsearcher.data.local.AppDatabase

class AppApplication : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val favoriteDao by lazy { database.favoriteDao() }
}