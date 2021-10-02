package com.example.herocomposeapp.data.model

class PowerStats (
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
    ){
    constructor() : this("", "", "","", "", "")
}
