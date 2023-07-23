package com.example.foodomer.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodomer.R
import com.example.foodomer.ui.components.core.BottomBar
import com.example.foodomer.ui.components.core.BottomBarItemProps
import com.example.foodomer.ui.screens.*
import com.example.foodomer.ui.theme.DEFAULT_PADDING

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    var isBottomBarShown by rememberSaveable { mutableStateOf(false) }

    isBottomBarShown = when (navBackStackEntry?.destination?.route) {
        Destinations.CreateCategory.route, Destinations.CreateFood.route, Destinations.FoodDetails.routeWithParams -> {
            false
        }

        else -> {
            true
        }
    }

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
                ),
                isShown = isBottomBarShown
            )
        },
        backgroundColor = Color.White
    ) { paddingValues ->
        val bottomPadding = paddingValues.calculateBottomPadding()
        Column (
            modifier = Modifier.fillMaxSize().padding(start = DEFAULT_PADDING, top = DEFAULT_PADDING, end = DEFAULT_PADDING, bottom = bottomPadding)
        ) {
            NavHost(
                navController,
                startDestination = Destinations.Home.route
            ) {
                composable(Destinations.Welcome.route) { WelcomeScreen(navController) }
                composable(Destinations.Home.route) { HomeScreen(navController) }
                composable(Destinations.Randomizer.route) { RandomizerScreen(navController) }
                composable(Destinations.CreateCategory.route) { CreateCategoryScreen(navController) }
                composable(Destinations.CreateFood.route) { CreateFoodScreen(navController) }
                composable(
                    route = Destinations.FoodDetails.routeWithParams,
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.let { FoodDetailsScreen(navController, it.getInt("id")) }
                }
                composable(Destinations.History.route) { RandomHistoryScreen(navController) }
            }
        }
    }
}