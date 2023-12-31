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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.metmuseum.ui.artScreen.ArtScreen
import com.example.metmuseum.ui.components.SideBarApp
import com.example.metmuseum.ui.components.BottomBarApp
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

    val backStackEntry by navController.currentBackStackEntryAsState()

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
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                ),
                title = {
                    Text("The Metropolitan Museum of Art")
                }
            )
        },
        bottomBar = {
            BottomBarApp(goHome, goToArt, goToFavorites)
        },
    ) { innerPadding ->
        NavigationComponent(navController = navController, modifier = Modifier.padding(innerPadding))

        /*
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            //ArtScreen()
        }*/
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