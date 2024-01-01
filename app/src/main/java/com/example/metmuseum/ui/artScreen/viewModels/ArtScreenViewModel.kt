package com.example.metmuseum.ui.artScreen.viewModels

import androidx.lifecycle.ViewModel
import com.example.metmuseum.model.Department
import com.example.metmuseum.ui.artScreen.state.ArtScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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