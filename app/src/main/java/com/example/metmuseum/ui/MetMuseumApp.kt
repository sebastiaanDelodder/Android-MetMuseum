package com.example.metmuseum.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.metmuseum.R
import com.example.metmuseum.ui.artScreen.ArtScreen
import com.example.metmuseum.ui.components.BottomBar
import com.example.metmuseum.ui.components.SideBarApp
import com.example.metmuseum.ui.components.TopBar
import com.example.metmuseum.ui.navigation.Destinations
import com.example.metmuseum.ui.navigation.NavigationComponent
import com.example.metmuseum.ui.util.NavigationType

@Composable
fun MetMuseumApp(
    navigationType: NavigationType,
) {
    val navController: NavHostController = rememberNavController()
    when (navigationType) {
        NavigationType.BOTTOM_NAVIGATION -> {
            MetMuseumAppPortrait(navController = navController)
        }

        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            MetMuseumAppLandscape(navController = navController)
        }

        NavigationType.NAVIGATION_RAIL -> {
            MetMuseumAppLandscape(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetMuseumAppPortrait(
    navController: NavHostController,
) {
    var selectedNavItem by remember { mutableIntStateOf(0) }

    //val canNavigateBack = navController.previousBackStackEntry != null
    //val navigateUp: () -> Unit = { navController.navigateUp() }

    val goHome: () -> Unit = {
        navController.popBackStack(
            Destinations.Home.name,
            inclusive = false,
        )
    }
    val goToArt = { navController.navigate(Destinations.Art.name) }
    val goToFavorites = { navController.navigate(Destinations.Favorites.name) }

    Scaffold(
        topBar = {
            TopBar(
                title = when (selectedNavItem) {
                    1 -> R.string.art
                    2 -> R.string.favorites
                    else -> R.string.museum
                },
            )
        },
        bottomBar = {
            BottomBar(
                goHome,
                goToArt,
                goToFavorites,
                selectedItem = selectedNavItem,
                onItemSelected = { index ->
                    selectedNavItem = index

                },)
        },
    ) { innerPadding ->
        NavigationComponent(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MetMuseumAppLandscape(
    navController: NavHostController,
) {
    NavigationComponent(navController = navController)
    Row{
        SideBarApp()
        //HomeScreen()
    }
}