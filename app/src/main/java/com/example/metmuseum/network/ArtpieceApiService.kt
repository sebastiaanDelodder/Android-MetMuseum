package com.example.metmuseum.network

import android.util.Log
import com.example.metmuseum.network.items.ApiDepartmentItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface ArtpieceApiService {
    @GET("artpieces")
    suspend fun getArtpieces(): List<ApiArtpiece>
}

// helper function
fun ArtpieceApiService.getArtpiecesAsFlow(): Flow<List<ApiArtpiece>> = flow {
    try {
        emit(getArtpieces())
    }
    catch(e: Exception){
        Log.e("API", "getArtpiecesAsFlow: "+e.stackTraceToString(), )
    }
}