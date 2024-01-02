package com.example.metmuseum.network

import com.example.metmuseum.network.items.ApiDepartmentItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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

object DepartmentApi {
    val retrofitService: DepartmentApiService by lazy {
        retrofit.create(DepartmentApiService::class.java)
    }
}