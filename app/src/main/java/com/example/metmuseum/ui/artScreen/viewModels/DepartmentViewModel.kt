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
import com.example.metmuseum.data.repository.DepartmentsRepository
import com.example.metmuseum.ui.artScreen.state.DepartmentApiState
import com.example.metmuseum.ui.artScreen.state.DepartmentListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class DepartmentViewModel(private val departmentsRepository: DepartmentsRepository) : ViewModel(){
    //private val _uiState = MutableStateFlow(DepartmentState(DepartmentSampler.getAll()))
    //val uiState: StateFlow<DepartmentState> = _uiState.asStateFlow()

    /*
    * Note: uiListState is a hot flow (.stateIn makes it so) --> it updates given a scope (viewmodelscope)
    * when no updates are required (lifecycle) the subscription is stopped after a timeout
    * */
    lateinit var uiListState : StateFlow<DepartmentListState>


    // keeping the state of the api request
    var departmentApiState: DepartmentApiState by mutableStateOf(DepartmentApiState.Loading)
        private set

    init {
        // initialize the uiListState
        getRepoDepartments()
        Log.i("vm inspection", "DepartmentViewModel init")
    }

    private fun getRepoDepartments(){
        try {
            viewModelScope.launch { departmentsRepository.refresh() }

            uiListState = departmentsRepository.getDepartments().map {
                Log.i("vm inspection", "DONE")
                DepartmentListState(it)
            }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = DepartmentListState()
                )
            Log.i("vm inspection", "DepartmentViewModel getRepoDepartments")
            Log.i("vm inspection", uiListState.value.departments.toString())

            departmentApiState = DepartmentApiState.Success
        }
        catch (e: IOException){
            //show a toast? save a log on firebase? ...
            //set the error state
            //TODO
            departmentApiState = DepartmentApiState.Error
        } catch (e: Exception){
            //show a toast? save a log on firebase? ...
            //set the error state
            //TODO
            departmentApiState = DepartmentApiState.Error
        } catch (e: Error){
            //show a toast? save a log on firebase? ...
            //set the error state
            //TODO
            departmentApiState = DepartmentApiState.Error
        }
    }

    /*
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
    }*/

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