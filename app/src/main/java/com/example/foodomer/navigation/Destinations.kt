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

    data object Welcome : Destinations("Welcome")
    data object Home : Destinations("Home")
    data object Randomizer : Destinations("Randomizer")
    data object CreateCategory : Destinations("Create-category")
    data object CreateFood : Destinations("Create-food")
    data object FoodDetails : Destinations("Food-details", "id")
    data object History : Destinations("History")
    data object Settings : Destinations("Settings")
    data object Search : Destinations("Search")
    data object QRCodeSharing : Destinations("QRCodeSharing", "data")
}