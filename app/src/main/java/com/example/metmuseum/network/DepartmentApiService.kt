package com.example.metmuseum.network

import android.util.Log
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.items.ApiDepartmentItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

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
        Log.e("API", "getDepartmentsAsFlow: "+e.stackTraceToString(), )
    }
}