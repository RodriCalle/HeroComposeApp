package com.example.herocomposeapp.data.model

import com.google.gson.annotations.SerializedName

class Hero (
    val id: String,
    val name: String,

    @SerializedName("powerstats")
    val powerstats: PowerStats,

    val biography: Biography,

    val appearance: Appearance,

    val image: Image
) {
    constructor() : this("", "", PowerStats(), Biography(), Appearance(), Image())
}