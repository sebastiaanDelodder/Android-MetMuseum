package com.example.metmuseum.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.metmuseum.R
import com.example.metmuseum.ui.artScreen.ArtScreen
import com.example.metmuseum.ui.components.BottomBar
import com.example.metmuseum.ui.components.NavigationDrawerContent
import com.example.metmuseum.ui.components.NavigationRail
import com.example.metmuseum.ui.components.SideBarApp
import com.example.metmuseum.ui.components.TopBar
import com.example.metmuseum.ui.navigation.Destinations
import com.example.metmuseum.ui.navigation.NavigationComponent
import com.example.metmuseum.ui.util.NavigationType

@Composable
fun MetMuseumApp(
    navigationType: NavigationType,
    navController: NavHostController = rememberNavController(),
) {
    var selectedNavItem by remember { mutableIntStateOf(0) }

    val goHome: () -> Unit = {
        navController.popBackStack(
            Destinations.Home.name,
            inclusive = false,
        )
    }

    val goToArt = { navController.navigate(Destinations.Art.name) }

    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(drawerContent = {
            PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                NavigationDrawerContent(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.inverseOnSurface)
                        .padding(dimensionResource(R.dimen.drawer_padding_content)),
                )
            }
        }) {
            Scaffold(
                //containerColor = Color.Transparent,
                topBar = {
                    TopBar(
                        title = when (selectedNavItem) {
                            1 -> R.string.art
                            else -> R.string.museum
                        },
                    )
                },
                // modifier = Modifier.padding(dimensionResource(id = R.dimen.drawer_width), 0.dp, 0.dp, 0.dp )
            ) { innerPadding ->
                NavigationComponent(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
    } else if (navigationType == NavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            //containerColor = Color.Transparent,
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
                    //goToFavorites,
                    selectedItem = selectedNavItem,
                    onItemSelected = { index ->
                        selectedNavItem = index

                    }
                )
            },
        ) { innerPadding ->
            NavigationComponent(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    } else {
        Row {
            AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                NavigationRail(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                )
            }
            Scaffold(
                //containerColor = Color.Transparent,
                topBar = {
                    TopBar(
                        title = when (selectedNavItem) {
                            1 -> R.string.art
                            2 -> R.string.favorites
                            else -> R.string.museum
                        },
                    )
                },
            ) { innerPadding ->
                NavigationComponent(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
    }
}