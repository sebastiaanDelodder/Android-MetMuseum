package com.example.metmuseum.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.example.metmuseum.ui.navigation.Destinations


/**
 * Composable function representing a custom navigation rail that displays navigation items.
 *
 * @param selectedDestination The currently selected navigation destination.
 * @param onTabPressed Callback function to be invoked when a navigation tab is pressed.
 * @param modifier Optional [Modifier] to apply to the navigation rail.
 *
 * @see Composable
 */
@Composable
fun NavigationRail(selectedDestination: NavDestination?, onTabPressed: (String) -> Unit, modifier: Modifier = Modifier) {
    NavigationRail(modifier = modifier) {
        for (navItem in Destinations.values()) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick = { onTabPressed(navItem.name) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                    )
                },
            )
        }
    }
}