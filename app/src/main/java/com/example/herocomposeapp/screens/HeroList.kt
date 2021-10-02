package com.example.herocomposeapp.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.herocomposeapp.data.model.Hero
import com.example.herocomposeapp.data.remote.ApiClient
import com.example.herocomposeapp.data.remote.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HeroList(navController: NavController) {
    var heroes by remember { mutableStateOf(listOf<Hero>()) }
    var name by remember { mutableStateOf("")}

    var focusManager = LocalFocusManager.current

    Column() {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) ,
            value = name,
            onValueChange = { name = it },
            leadingIcon = { Icon(Icons.Filled.Search , contentDescription = null)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                val apiService = ApiClient.build()
                val getHeroesByName = apiService.getHerosByName(name)

                getHeroesByName.enqueue(object: Callback<ApiResponse> {
                    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                        if (response.isSuccessful){
                            heroes = response.body()!!.heroes
                            focusManager.clearFocus()
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.d("Heroes", t.message.toString())
                    }
                })
            })
        )
        LazyColumn(){
            items(heroes){
                    hero -> HeroRow(hero, navController)
            }
        }
    }
}

@Composable
fun HeroRow(hero: Hero, navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { navController.navigate("HeroProfile/${hero.id}") }) {
        HeroImage(hero)
        Column {
            Text(hero.name)
            Text(hero.biography.fullName)
        }
    }
}
