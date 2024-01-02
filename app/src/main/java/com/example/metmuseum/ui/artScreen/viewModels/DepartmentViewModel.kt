package com.example.metmuseum.ui.artScreen.viewModels

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.metmuseum.Application
import com.example.metmuseum.data.DepartmentSampler
import com.example.metmuseum.data.repository.ApiDepartmentsRepository
import com.example.metmuseum.data.repository.DepartmentsRepository
import com.example.metmuseum.network.DepartmentApi
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.ui.artScreen.state.DepartmentApiState
import com.example.metmuseum.ui.artScreen.state.DepartmentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class DepartmentViewModel(private val departmentsRepository: DepartmentsRepository) : ViewModel(){
    private val _uiState = MutableStateFlow(DepartmentState(DepartmentSampler.getAll()))
    val uiState: StateFlow<DepartmentState> = _uiState.asStateFlow()

    // keeping the state of the api request
    var departmentApiState: DepartmentApiState by mutableStateOf(DepartmentApiState.Loading)
        private set

    init {
        getApiDepartments()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getApiDepartments() {
        viewModelScope.launch {
            try {
                val listResult = departmentsRepository.getDepartments()
                _uiState.update {
                    it.copy(
                        currentDepartments = listResult
                    )
                }
                departmentApiState = DepartmentApiState.Success(listResult)
            } catch (e: IOException) {
                departmentApiState = DepartmentApiState.Error
            } catch (e: HttpException){
                departmentApiState = DepartmentApiState.Error
            }
        }
    }

    //object to tell the android framework how to handle the parameter of the viewmodel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                val departmentsRepository = application.container.departmentsRepository
                DepartmentViewModel(
                    departmentsRepository = departmentsRepository
                )
            }
        }
    }
}