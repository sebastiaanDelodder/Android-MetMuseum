package com.example.metmuseum.data.repository

import com.example.metmuseum.model.Department
import com.example.metmuseum.network.DepartmentApiService
import com.example.metmuseum.network.asDomainObjects

interface DepartmentsRepository {
    suspend fun getDepartments(): List<Department>
}

class ApiDepartmentsRepository(
    private val departmentApiService: DepartmentApiService
): DepartmentsRepository{
    override suspend fun getDepartments(): List<Department> {
        return departmentApiService.getDepartments().departments.asDomainObjects()
    }
}