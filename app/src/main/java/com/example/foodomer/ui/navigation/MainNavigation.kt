package com.example.foodomer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodomer.ui.screens.WelcomeScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = "welcome"
    ) {
        composable("welcome") { WelcomeScreen(navController) }
    }
}