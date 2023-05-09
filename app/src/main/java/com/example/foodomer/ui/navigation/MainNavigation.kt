package com.example.foodomer.ui.navigation

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
        startDestination = "home"
    ) {
        composable("welcome") { WelcomeScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("randomizer") { RandomizerScreen(navController) }
        composable("create-category") { CreateCategoryScreen(navController) }
        composable("create-food") { CreateFoodScreen(navController) }
    }
}