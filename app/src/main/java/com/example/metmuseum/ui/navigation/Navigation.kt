package com.example.metmuseum.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.metmuseum.ui.artScreen.ArtScreen
import com.example.metmuseum.ui.artScreen.DepartmentScreen
import com.example.metmuseum.ui.favoritesScreen.FavoritesScreen
import com.example.metmuseum.ui.homeScreen.HomeScreen

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
        /*
        composable(route = Destinations.Favorites.name) {
            FavoritesScreen()
        }

         */
    }
}