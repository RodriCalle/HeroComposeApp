package com.example.herocomposeapp.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.herocomposeapp.data.local.AppDatabase
import com.example.herocomposeapp.data.model.Favorite
import com.example.herocomposeapp.data.model.Hero
import com.example.herocomposeapp.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HeroProfile(id: String) {
    var hero by remember { mutableStateOf(Hero()) }

    val apiService = ApiClient.build()
    val getHeroById = apiService.getHeroById(id)

    getHeroById.enqueue(object: Callback<Hero> {
        override fun onResponse(call: Call<Hero>, response: Response<Hero>) {
            if(response.isSuccessful){
                hero = response.body()!!
            }
        }

        override fun onFailure(call: Call<Hero>, t: Throwable) {
            Log.d("Hero", t.message.toString())
        }

    })

    HeroItem(hero)
}

@Composable
fun HeroItem(hero: Hero) {
    val context = LocalContext.current
    var favorite by remember {
        mutableStateOf(false)
    }

    favorite = AppDatabase.getInstance(context).heroDao().getHeroById(hero.id) != null
    
    Column(modifier = Modifier.border(BorderStroke(2.dp,color = Color.Red)),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        HeroImage(hero)
        HeroBiography(hero)
        HeroPowers(hero)

        IconButton(onClick =
        {
            if(favorite){
                AppDatabase.getInstance(context).heroDao().delete(Favorite(hero.id, hero.name))
            } else {
                AppDatabase.getInstance(context).heroDao().insert(Favorite(hero.id, hero.name))
            }
            favorite = !favorite
        }) {
            Icon(Icons.Filled.Favorite, null, 
            tint = if (favorite) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
fun HeroBiography(hero: Hero) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .border(BorderStroke(2.dp,color = Color.Green))) {
        Column(modifier = Modifier.background(Color.Gray)) {
            Text(text = "Biography", fontSize = 15.sp, color = Color.Red)
            Text(text = "Name: ${hero.name}")
            Text(text = "FullName: ${hero.biography.fullName}")
            Text(text = "Place of Birth: ${hero.biography.placeOfBirth}")
            Text(text = "First appearance: ${hero.biography.firstAppearance}")
            Text(text = "Publisher: ${hero.biography.publisher}")
        }
    }
}

@Composable
fun HeroPowers(hero: Hero) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .border(BorderStroke(2.dp,color = Color.Blue))) {
        Column() {
            Text(text = "Hero Power Stats", fontSize = 15.sp, color = Color.Red)
            Text(text = "Intelligence: ${hero.powerstats.intelligence}")
            Text(text = "Strength: ${hero.powerstats.strength}")
            Text(text = "Speed: ${hero.powerstats.speed}")
            Text(text = "Durability: ${hero.powerstats.durability}")
            Text(text = "Power: ${hero.powerstats.power}")
            Text(text = "Combat: ${hero.powerstats.combat}")
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HeroImage(hero: Hero) {
    Card(modifier = Modifier
        .padding(8.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Image of Hero",fontSize = 20.sp, color = Color.Red)
            Image(painter = rememberImagePainter(data = hero.image.url),
                contentDescription = null,
                modifier = Modifier.size(128.dp))
        }
    }
}
