package com.example.herocomposeapp.data.remote

import com.example.herocomposeapp.data.model.Hero
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{id}")
    fun getHeroById(@Path("id") id: String): Call<Hero>

    @GET("search/{name}")
    fun getHerosByName(@Path("name") name: String): Call<ApiResponse>
}