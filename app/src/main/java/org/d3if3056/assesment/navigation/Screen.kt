package org.d3if3056.assesment.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("MainScreen")
}