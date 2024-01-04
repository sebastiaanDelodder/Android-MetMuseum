package com.example.metmuseum.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ImportContacts
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.metmuseum.R

enum class Destinations(@StringRes val title: Int, val icon: ImageVector) {
    Home(title = R.string.home, icon = Icons.Default.Home),
    Art(title = R.string.art, icon = Icons.Outlined.ImportContacts),
}