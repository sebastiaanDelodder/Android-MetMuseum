package com.example.metmuseum.network

import com.example.metmuseum.model.Department
import kotlinx.serialization.Serializable

@Serializable
data class ApiDepartment(
    val departmentId: Int,
    val displayName: String
) {}

fun List<ApiDepartment>.asDomainObjects(): List<Department> {
    var domainList = this.map {
        Department(
            departmentId = it.departmentId,
            displayName = it.displayName
        )
    }
    return domainList
}