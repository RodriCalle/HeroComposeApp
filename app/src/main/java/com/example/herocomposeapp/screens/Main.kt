package com.example.herocomposeapp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Main() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home"){
        composable("Home") { Home(navController) }

        composable("HeroProfile/{id}",
            arguments = listOf(navArgument("id")
            { type = NavType.StringType}))
        { backStackEntry -> val id = backStackEntry.arguments!!.getString("id", "1")
            HeroProfile(id) }

        composable("HeroList") { HeroList(navController) }
    }
}
