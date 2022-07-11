package com.example.booster.navigation

sealed class Screen(val route: String, val title: String) {
    object TypeScreen: Screen(route = "type", title = "Investor Type")
    object HomeScreen: Screen(route = "home", title = "Home")
    object FormScreen: Screen(route = "form", title = "Form")
}
