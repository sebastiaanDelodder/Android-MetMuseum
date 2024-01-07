package com.example.metmuseum.network.items

import com.example.metmuseum.network.ApiDepartment
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing an item containing a list of API Department entities.
 *
 * This class is annotated with [Serializable] to enable serialization and deserialization.
 *
 * @property departments List of [ApiDepartment] entities.
 *
 * @see Serializable
 * @see ApiDepartment
 */
@Serializable
data class ApiDepartmentItem (
    val departments: List<ApiDepartment>
)