package com.example.metmuseum.ui.artScreen.viewModels

import androidx.lifecycle.ViewModel
import com.example.metmuseum.model.Department
import com.example.metmuseum.ui.artScreen.state.ArtScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel class for managing the UI state related to the Art Screen.
 *
 * This ViewModel maintains the UI state using a [MutableStateFlow] named [_uiState]. The current UI state
 * is exposed as an immutable [StateFlow] named [uiState].
 *
 * @property _uiState MutableStateFlow representing the mutable UI state.
 * @property uiState Immutable StateFlow representing the current UI state.
 *
 * @constructor Creates an instance of [ArtScreenViewModel].
 *
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 * @see ArtScreenState
 */
class ArtScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ArtScreenState())
    val uiState: StateFlow<ArtScreenState> = _uiState.asStateFlow()

    fun setDepartment(department: Department? ){
        _uiState.update {
            currentState ->
            currentState.copy(currentDepartment = department)
        }
    }
}