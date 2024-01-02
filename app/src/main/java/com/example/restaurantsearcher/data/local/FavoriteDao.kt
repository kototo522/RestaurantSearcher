package com.example.restaurantsearcher.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

//    @Query("SELECT * FROM favorite_table WHERE itemId = :itemId")
//    suspend fun getFavorite(itemId: String): FavoriteEntity?
}