package com.example.metmuseum.ui.artScreen

import androidx.lifecycle.ViewModel
import com.example.metmuseum.data.DepartmentSampler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DepartmentViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(DepartmentState(DepartmentSampler.getAll()))
    val uiState: StateFlow<DepartmentState> = _uiState.asStateFlow()

}