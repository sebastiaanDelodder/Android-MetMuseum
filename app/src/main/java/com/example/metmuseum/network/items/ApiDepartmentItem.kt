package com.example.metmuseum.network.items

import com.example.metmuseum.model.Department
import com.example.metmuseum.network.ApiDepartment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiDepartmentItem (
    val departments: List<ApiDepartment>
){}