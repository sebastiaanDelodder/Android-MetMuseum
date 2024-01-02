package com.example.metmuseum.ui.artScreen.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metmuseum.data.DepartmentSampler
import com.example.metmuseum.network.DepartmentApi
import com.example.metmuseum.ui.artScreen.state.DepartmentApiState
import com.example.metmuseum.ui.artScreen.state.DepartmentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class DepartmentViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(DepartmentState(DepartmentSampler.getAll()))
    val uiState: StateFlow<DepartmentState> = _uiState.asStateFlow()

    // keeping the state of the api request
    var departmentApiState: DepartmentApiState by mutableStateOf(DepartmentApiState.Loading)
        private set

    init {
        getApiDepartments()
    }
    private fun getApiDepartments() {
        viewModelScope.launch {
            try {
                val listResult = DepartmentApi.retrofitService.getDepartments()
                _uiState.update {
                    it.copy(
                        departments = listResult
                    )
                }
                departmentApiState = DepartmentApiState.Success(listResult)
            } catch (e: IOException) {
                departmentApiState = DepartmentApiState.Error
            }
        }
    }
}