package com.example.metmuseum.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ImportContacts
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.metmuseum.R


/**
 * Composable function representing the main navigation screen.
 *
 * This composable function creates a bottom navigation bar using the [BottomBar] composable.
 *
 * @see Preview
 * @see Composable
 */
@Preview
@Composable
fun Nav() {
    BottomBar(goHome = {}, goToArt = {}, selectedItem = 0, onItemSelected = {})
}


/**
 * Composable function representing a customizable bottom navigation bar.
 *
 * This composable function creates a bottom navigation bar with multiple items.
 *
 * @param goHome Lambda function to execute when the "Home" item is selected.
 * @param goToArt Lambda function to execute when the "Art" item is selected.
 * @param selectedItem The index of the currently selected item.
 * @param onItemSelected Lambda function to execute when any item is selected. It receives the index of the selected item.
 * @param modifier Modifier for styling and layout customization.
 *
 * @see Composable
 * @see NavigationBar
 * @see NavigationBarItem
 */
@Composable
fun BottomBar(
    goHome: () -> Unit,
    goToArt: () -> Unit,
    selectedItem : Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Navigate to Home",
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.home),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            selected = selectedItem == 0,
            onClick = {
                onItemSelected(0)
                goHome()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onSurface,
                indicatorColor = MaterialTheme.colorScheme.surface,
                unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
            ),
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ImportContacts,
                    contentDescription = "Navigate to Art",
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.art),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            selected = selectedItem == 1,
            onClick = {
                onItemSelected(1)
                goToArt()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onSurface,
                indicatorColor = MaterialTheme.colorScheme.surface,
                unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
            ),
        )
    }
}