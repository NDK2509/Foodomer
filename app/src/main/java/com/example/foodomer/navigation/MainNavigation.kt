package com.example.foodomer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodomer.ui.screens.*

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = Destinations.Home.route
    ) {
        composable(Destinations.Welcome.route) { WelcomeScreen(navController) }
        composable(Destinations.Home.route) { HomeScreen(navController) }
        composable(Destinations.Randomizer.route) { RandomizerScreen(navController) }
        composable(Destinations.CreateCategory.route) { CreateCategoryScreen(navController) }
        composable(Destinations.CreateFood.route) { CreateFoodScreen(navController) }
    }
}