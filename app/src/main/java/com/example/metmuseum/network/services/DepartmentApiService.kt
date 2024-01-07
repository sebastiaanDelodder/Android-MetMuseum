package com.example.metmuseum.network.services

import android.util.Log
import com.example.metmuseum.network.ApiDepartment
import com.example.metmuseum.network.items.ApiDepartmentItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

/**
 * Interface defining API calls related to Departments.
 *
 * This interface provides methods for fetching Departments from a remote API.
 */
interface DepartmentApiService {

    /**
     * Fetches a list of departments from the API.
     *
     * @return An [ApiDepartmentItem] containing the list of departments.
     */
    @GET("departments")
    suspend fun getDepartments(): ApiDepartmentItem
}

/**
 * Helper function to fetch Departments and emit their objectIDs as a [Flow].
 *
 * This function wraps the synchronous API call in a flow and emits the list of departments.
 * In case of an exception during the API call, an error message is logged.
 *
 * @return A [Flow] emitting a list of objectIDs for the fetched Artpieces.
 */
fun DepartmentApiService.getDepartmentsAsFlow(): Flow<List<ApiDepartment>> = flow {
    try {
        emit(getDepartments().departments)
    }
    catch(e: Exception){
        Log.e("DepartmentApiService", e.stackTraceToString())
    }
}