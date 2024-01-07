package com.example.metmuseum.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.metmuseum.ui.artScreen.ArtScreen
import com.example.metmuseum.ui.homeScreen.HomeScreen

/**
 * Composable function representing a navigation component using Jetpack Navigation.
 *
 * This composable sets up a [NavHost] with specified destinations and associates them with corresponding screens.
 *
 * @param navController The [NavHostController] used for navigation within the component.
 * @param modifier The modifier to be applied to the composable.
 *
 * @see Composable
 * @see NavHost
 * @see Destinations
 */
@Composable
fun NavigationComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.name,
        modifier = modifier
    ){
        composable(route = Destinations.Home.name) {
            HomeScreen()
        }
        composable(route = Destinations.Art.name) {
            ArtScreen()
        }
    }
}