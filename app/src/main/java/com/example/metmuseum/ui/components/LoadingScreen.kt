package com.example.metmuseum.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A composable function representing a loading screen that displays a simple "Loading..." text.
 *
 * @param modifier The modifier to be applied to the composable.
 *
 * @see Composable
 */
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Text(text = "Loading...", modifier = modifier)
}