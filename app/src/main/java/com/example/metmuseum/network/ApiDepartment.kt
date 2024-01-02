package com.example.metmuseum.network

import com.example.metmuseum.model.Department
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiDepartment(
    val departmentId: Int,
    val displayName: String
) {}

fun Flow<List<ApiDepartment>>.asDomainObjects(): Flow<List<Department>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiDepartment>.asDomainObjects(): List<Department> {
    var domainList = this.map {
        Department(
            departmentId = it.departmentId,
            displayName = it.displayName
        )
    }
    return domainList
}