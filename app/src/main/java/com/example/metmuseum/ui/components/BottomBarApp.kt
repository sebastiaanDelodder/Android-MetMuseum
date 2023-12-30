package com.example.metmuseum.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.metmuseum.R

@Preview
@Composable
fun Nav() {
    BottomBarApp()
}

@Composable
fun BottomBarApp(
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
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.home),
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            selected = true,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                indicatorColor = MaterialTheme.colorScheme.surface,
                unselectedIconColor = MaterialTheme.colorScheme.secondary,
            ),
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.art),
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                indicatorColor = MaterialTheme.colorScheme.surface,
                unselectedIconColor = MaterialTheme.colorScheme.secondary,
            ),
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.favorites),
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                indicatorColor = MaterialTheme.colorScheme.surface,
                unselectedIconColor = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
}