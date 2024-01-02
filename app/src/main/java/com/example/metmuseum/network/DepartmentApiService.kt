package com.example.metmuseum.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DepartmentApiService {
    @GET("departments")
    suspend fun getDepartments(): String
}

object DepartmentApi {
    val retrofitService: DepartmentApiService by lazy {
        retrofit.create(DepartmentApiService::class.java)
    }
}