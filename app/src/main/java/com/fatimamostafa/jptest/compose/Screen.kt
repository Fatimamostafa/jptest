package com.fatimamostafa.jptest.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList(),
) {
    data object PlanetScreen : Screen("planetScreen")

    data object PlanetDetail : Screen(
        route = "planetDetail/{planetName}",
        navArguments =
        listOf(
            navArgument("planetName") {
                type = NavType.StringType
            },
        ),

        ) {
        fun createRoute(planetName: String) = "planetDetail/$planetName"
    }

}