package com.example.metmuseum.network.items

import com.example.metmuseum.network.ApiDepartment
import kotlinx.serialization.Serializable

@Serializable
data class ApiDepartmentItem (
    val departments: List<ApiDepartment>
){}