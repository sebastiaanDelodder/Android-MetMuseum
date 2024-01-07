package com.example.metmuseum.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ImportContacts
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.metmuseum.R

/**
 * Enum class representing various destinations within the application.
 *
 * Each destination has a corresponding title resource ID and an associated icon.
 *
 * @property title The resource ID for the title of the destination.
 * @property icon The vector image representing the icon of the destination.
 */
enum class Destinations(@StringRes val title: Int, val icon: ImageVector) {
    /**
     * Represents the Home destination.
     */
    Home(title = R.string.home, icon = Icons.Default.Home),

    /**
     * Represents the Art destination.
     */
    Art(title = R.string.art, icon = Icons.Outlined.ImportContacts),
}