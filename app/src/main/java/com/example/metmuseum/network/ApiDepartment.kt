package com.example.metmuseum.network

import com.example.metmuseum.model.Department
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

/**
 * Represents a department entity retrieved from the API.
 *
 * @property departmentId The unique identifier for the department.
 * @property displayName The display name of the department.
 */
@Serializable
data class ApiDepartment(
    val departmentId: Int,
    val displayName: String
)

/**
 * Converts a Flow of lists of [ApiDepartment] objects to a Flow of lists of [Department] objects.
 *
 * This extension function is used to transform the data retrieved from the API into domain objects.
 *
 * @return A [Flow] emitting a list of [Department] objects.
 */
fun Flow<List<ApiDepartment>>.asDomainObjects(): Flow<List<Department>> {
    return map {
        it.asDomainObjects()
    }
}

/**
 * Converts a list of [ApiDepartment] objects to a list of [Department] objects.
 *
 * This extension function is used to transform the data retrieved from the API into domain objects.
 *
 * @return A list of [Department] objects.
 */
fun List<ApiDepartment>.asDomainObjects(): List<Department> {
    var domainList = this.map {
        Department(
            departmentId = it.departmentId,
            displayName = it.displayName
        )
    }
    return domainList
}