package com.example.metmuseum.ui.artScreen.state

import com.example.metmuseum.model.Department

/**
 * Represents the state of a list of [Department] entities in the user interface.
 *
 * @property departments The list of [Department] entities in the current state. Defaults to an empty list.
 */
data class DepartmentListState(
    val departments: List<Department> = listOf()
)

/**
 * Represents the different states of the Department API call.
 *
 * This is a sealed interface, and its implementations define the possible states of the API call.
 */
sealed interface DepartmentApiState {
    /**
     * Represents a successful state of the Department API call.
     */
    object Success : DepartmentApiState

    /**
     * Represents an error state of the Department API call.
     */
    object Error: DepartmentApiState

    /**
     * Represents a loading state of the Department API call.
     */
    object Loading : DepartmentApiState
}