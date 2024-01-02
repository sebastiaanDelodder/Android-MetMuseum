package com.example.metmuseum.data

import android.content.Context
import com.example.metmuseum.data.database.departments.DepartmentDb
import com.example.metmuseum.data.repository.CachingDepartmentsRepository
import com.example.metmuseum.data.repository.DepartmentsRepository
import com.example.metmuseum.network.DepartmentApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val departmentsRepository: DepartmentsRepository
}

class DefaultAppContainer(private val context: Context): AppContainer {
    private val baseUrl = "https://collectionapi.metmuseum.org/public/collection/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : DepartmentApiService by lazy {
        retrofit.create(DepartmentApiService::class.java)
    }

    override val departmentsRepository: DepartmentsRepository by lazy {
        CachingDepartmentsRepository(DepartmentDb.getDatabase(context = context).departmentDao(), retrofitService)
    }
}