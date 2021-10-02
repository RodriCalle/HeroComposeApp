package com.example.herocomposeapp.data.model

import com.google.gson.annotations.SerializedName

class Biography (
    @SerializedName("full-name")
    val fullName: String,

    @SerializedName("place-of-birth")
    val placeOfBirth: String,

    @SerializedName("first-appearance")
    val firstAppearance: String,

    val publisher: String
    ) {
    constructor() : this("", "", "","")
}
