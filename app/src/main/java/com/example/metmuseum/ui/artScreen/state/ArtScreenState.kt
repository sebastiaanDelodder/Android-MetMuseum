package com.example.metmuseum.ui.artScreen.state

import com.example.metmuseum.model.Department

/**
 * Data class representing the state of the art screen.
 *
 * @property currentDepartment The currently selected [Department], or `null` if no department is selected.
 */
data class ArtScreenState(
    val currentDepartment : Department? = null,
)