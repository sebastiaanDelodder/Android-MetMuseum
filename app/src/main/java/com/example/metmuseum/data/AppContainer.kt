package com.example.metmuseum.data

import android.content.Context
import com.example.metmuseum.data.database.MetArtDb
import com.example.metmuseum.data.repository.ArtpiecesRepository
import com.example.metmuseum.data.repository.CachingArtpiecesRepository
import com.example.metmuseum.data.repository.CachingDepartmentsRepository
import com.example.metmuseum.data.repository.DepartmentsRepository
import com.example.metmuseum.network.services.ArtpieceApiService
import com.example.metmuseum.network.services.DepartmentApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Interface representing the dependency container for the application.
 * It provides instances of various repositories needed by the app.
 */
interface AppContainer {
    /**
     * Provides the instance of the [DepartmentsRepository].
     */
    val departmentsRepository: DepartmentsRepository

    /**
     * Provides the instance of the [ArtpiecesRepository].
     */
    val artpiecesRepository: ArtpiecesRepository
}

/**
 * Default implementation of the [AppContainer] interface.
 * This class initializes and provides instances of repositories required by the app.
 *
 * @param context The application context used for initializing dependencies.
 */
class DefaultAppContainer(private val context: Context): AppContainer {

    /**
     * Base URL for the Retrofit client.
     */
    private val baseUrl = "https://collectionapi.metmuseum.org/public/collection/v1/"

    /**
     * Retrofit instance used for API communication.
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Lazy-initialized Retrofit service for accessing department-related APIs.
     */
    private val retrofitDepartmentService : DepartmentApiService by lazy {
        retrofit.create(DepartmentApiService::class.java)
    }

    /**
     * Provides the instance of [DepartmentsRepository].
     */
    override val departmentsRepository: DepartmentsRepository by lazy {
        CachingDepartmentsRepository(MetArtDb.getDatabase(context = context).departmentDao(), retrofitDepartmentService)
    }

    /**
     * Lazy-initialized Retrofit service for accessing artpiece-related APIs.
     */
    private val retrofitArtpieceService : ArtpieceApiService by lazy {
        retrofit.create(ArtpieceApiService::class.java)
    }

    /**
     * Provides the instance of [ArtpiecesRepository].
     */
    override val artpiecesRepository: ArtpiecesRepository by lazy {
        CachingArtpiecesRepository(MetArtDb.getDatabase(context = context).artpieceDao(), retrofitArtpieceService)
    }
}