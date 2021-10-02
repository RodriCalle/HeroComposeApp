package com.example.herocomposeapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
class Favorite(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "favorite_name")
    val name: String
)