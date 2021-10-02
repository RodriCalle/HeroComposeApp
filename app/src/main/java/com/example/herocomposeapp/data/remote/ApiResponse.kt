package com.example.herocomposeapp.data.remote

import com.example.herocomposeapp.data.model.Hero
import com.google.gson.annotations.SerializedName

class ApiResponse (
    @SerializedName("results")
    val heroes: List<Hero>
)
