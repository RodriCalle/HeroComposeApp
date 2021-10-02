package com.example.herocomposeapp.data.model

import com.google.gson.annotations.SerializedName

class Appearance (
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,

    @SerializedName("eye-color")
    val eyeColor: String,

    @SerializedName("hair-color")
    val hairColor: String
    ){
    constructor() : this("", "", emptyList(), emptyList(),"","")
}
