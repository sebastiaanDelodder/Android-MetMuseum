package com.example.metmuseum.ui.artScreen.viewModels

import android.util.Log
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel class responsible for managing the UI-related data of the Department feature.
 *
 * This class interacts with the [DepartmentsRepository] to handle data operations and provides
 * the UI with the necessary information through the [uiListState] StateFlow and [departmentApiState].
 *
 * @property departmentsRepository Repository for handling data operations related to departments.
 * @property uiListState StateFlow representing the UI state of the department list.
 * @property departmentApiState State representing the state of the API request for department data.
 *
 * @constructor Creates a [DepartmentViewModel] with the provided [departmentsRepository].
 * @see ViewModel
 */
class DepartmentViewModel(private val departmentsRepository: DepartmentsRepository) : ViewModel(){

    /**
     * StateFlow representing the UI state of the department list.
     *
     * Note: [uiListState] is a hot flow (.stateIn makes it so) — it updates given a scope (viewmodelscope).
     * When no updates are required (lifecycle), the subscription is stopped after a timeout.
     */
    lateinit var uiListState : StateFlow<DepartmentListState>


    /**
     * State representing the state of the API request for department data.
     */
    var departmentApiState: DepartmentApiState by mutableStateOf(DepartmentApiState.Loading)
        private set

    /**
     * Initializes the ViewModel by fetching and updating the department data.
     */
    init {
        getRepoDepartments()
        Log.i("vm inspection", "DepartmentViewModel init")
    }

    /**
     * Retrieves and updates the department data from the repository.
     */
    private fun getRepoDepartments(){
        try {
            viewModelScope.launch { departmentsRepository.refresh() }

            uiListState = departmentsRepository.getDepartments().map {
                DepartmentListState(it)
            }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = DepartmentListState()
                )

            departmentApiState = DepartmentApiState.Success
        }
        catch (e: IOException){
            Log.e("DepartmentViewModel", e.stackTraceToString())
            departmentApiState = DepartmentApiState.Error
        } catch (e: Exception){
            Log.e("DepartmentViewModel", e.stackTraceToString())
            departmentApiState = DepartmentApiState.Error
        } catch (e: Error){
            Log.e("DepartmentViewModel", e.stackTraceToString())
            departmentApiState = DepartmentApiState.Error
        }
    }

    /**
     * Companion object to provide a [ViewModelProvider.Factory] for creating instances of [DepartmentViewModel].
     */
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