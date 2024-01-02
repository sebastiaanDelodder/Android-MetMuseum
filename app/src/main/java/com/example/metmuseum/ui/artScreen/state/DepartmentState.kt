package com.example.metmuseum.ui.artScreen.state

import com.example.metmuseum.model.Department

/*
data class DepartmentState(
    //val currentDepartments: List<Department>
)*/

data class DepartmentListState(
    val departments: List<Department> = listOf()
)

sealed interface DepartmentApiState {
    object Success : DepartmentApiState
    object Error: DepartmentApiState
    object Loading : DepartmentApiState
}