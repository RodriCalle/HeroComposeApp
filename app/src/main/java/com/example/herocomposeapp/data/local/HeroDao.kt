package com.example.herocomposeapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.herocomposeapp.data.model.Favorite
import com.example.herocomposeapp.data.model.Hero

@Dao
interface HeroDao {

    @Query("select * from favorites where id =:id")
    fun getHeroById(id: String): Favorite

    @Insert
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}