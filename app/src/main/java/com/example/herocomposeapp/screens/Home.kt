package com.example.herocomposeapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center) {
        Button(onClick = { navController.navigate("HeroProfile/1") },
            modifier = Modifier.fillMaxWidth()
                .background(color = Color.Yellow)
                .padding(10.dp)) {
            Text(text = "Hero Profiles")
        }

        Button(onClick = { navController.navigate("HeroList") },
            modifier = Modifier.background(color = Color.LightGray)
                .fillMaxWidth()) {
            Text(text = "Search Heros")
        }
        
    }

}