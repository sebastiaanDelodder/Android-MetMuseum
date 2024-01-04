package com.example.metmuseum.network.services

import android.util.Log
import com.example.metmuseum.network.ApiDepartment
import com.example.metmuseum.network.items.ApiDepartmentItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET


interface DepartmentApiService {
    @GET("departments")
    suspend fun getDepartments(): ApiDepartmentItem
}

// helper function
fun DepartmentApiService.getDepartmentsAsFlow(): Flow<List<ApiDepartment>> = flow {
    try {
        emit(getDepartments().departments)
    }
    catch(e: Exception){
        Log.e("DepartmentApiService", "getDepartmentsAsFlow: "+e.stackTraceToString(), )
    }
}