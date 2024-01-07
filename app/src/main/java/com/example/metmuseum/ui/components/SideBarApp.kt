package com.example.metmuseum.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ImportContacts
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.metmuseum.R

/**
 * A composable function to display the navigation sidebar in a preview.
 *
 * This composable function is annotated with @Preview and @Composable.
 */
@Preview
@Composable
fun NavSide() {
    SideBarApp()
}


/**
 * A composable function to create a navigation sidebar using Jetpack Compose.
 *
 * @param modifier The modifier to apply to the navigation sidebar.
 */
@Composable
fun SideBarApp(
    modifier: Modifier = Modifier
) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.home),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                selected = true,
                onClick = {},
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.surface,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.ImportContacts,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.art),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                selected = false,
                onClick = {},
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.surface,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        }
    }
}