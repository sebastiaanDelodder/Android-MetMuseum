package com.example.metmuseum.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavDestination
import com.example.metmuseum.R
import com.example.metmuseum.ui.navigation.Destinations

/**
 * Composable function for rendering the content of a navigation drawer.
 *
 * This composable displays a list of navigation items based on the provided [Destinations] enum.
 * It highlights the selected item and invokes the [onTabPressed] callback when an item is clicked.
 *
 * @param selectedDestination The currently selected navigation destination.
 * @param onTabPressed Callback to be invoked when a navigation item is clicked. It receives the route of the clicked item.
 * @param modifier Modifier for styling or positioning the navigation drawer content.
 */
@Composable
fun NavigationDrawerContent(
    selectedDestination: NavDestination?,
    onTabPressed: ((String) -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        for (navItem in Destinations.values()) {
            NavigationDrawerItem(
                selected = selectedDestination?.route == navItem.name,
                label = {
                    Text(
                        text = navItem.name,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.drawer_padding_header)),
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent,
                ),
                onClick = { onTabPressed(navItem.name) },
            )
        }
    }
}