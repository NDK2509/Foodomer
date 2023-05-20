package com.example.foodomer.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodomer.R
import com.example.foodomer.ui.components.core.BottomBar
import com.example.foodomer.ui.components.core.BottomBarItemProps
import com.example.foodomer.ui.screens.*

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                items = listOf(
                    BottomBarItemProps(
                        icon = painterResource(R.drawable.icon_home), destination = Destinations.Home.route
                    ),
                    BottomBarItemProps(
                        icon = painterResource(R.drawable.icon_cube), destination = Destinations.Randomizer.route
                    ),
                    BottomBarItemProps(
                        icon = painterResource(R.drawable.icon_clock), destination = Destinations.History.route
                    )
                )
            )
        },
        backgroundColor = Color.White
    ) { _ ->
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
}