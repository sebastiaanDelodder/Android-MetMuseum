package com.example.metmuseum.ui.artScreen.viewModels

import androidx.lifecycle.ViewModel
import com.example.metmuseum.data.DepartmentSampler
import com.example.metmuseum.ui.artScreen.state.DepartmentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DepartmentViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(DepartmentState(DepartmentSampler.getAll()))
    val uiState: StateFlow<DepartmentState> = _uiState.asStateFlow()
}