package com.example.metmuseum.ui.artScreen

import com.example.metmuseum.model.Department

data class DepartmentState(
    val currentDepartments: List<Department>
)

data class DepartmentListState(
    val departments: List<Department> = listOf()
)