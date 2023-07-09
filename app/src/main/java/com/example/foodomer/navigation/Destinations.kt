package com.example.foodomer.navigation

sealed class Destinations(val route: String, vararg params: String) {
    val routeWithParams: String = if (params.isNotEmpty()) {
        this.route + params.joinToString { "/{$it}" }
    } else this.route

    fun routeWithFilledParams(vararg params: Any): String {
        return if (params.isNotEmpty()) {
            this.route + params.joinToString { "/$it" }
        } else this.route
    }

    object Welcome : Destinations("Welcome")
    object Home : Destinations("Home")
    object Randomizer : Destinations("Randomizer")
    object CreateCategory : Destinations("Create-category")
    object CreateFood : Destinations("Create-food")
    object FoodDetails : Destinations("Food-details", "id")
    object History : Destinations("History")
    object Settings : Destinations("Settings")
    object Search : Destinations("Search")
}