package com.example.metmuseum.network

import android.util.Log
import com.example.metmuseum.network.items.ApiArtpieceItem
import com.example.metmuseum.network.items.ApiDepartmentItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtpieceApiService {
    @GET("objects")
    suspend fun getArtpieces(@Query("departmentIds") departmentId: Int): ApiArtpieceItem

    @GET("objects/{objectId}")
    suspend fun getArtpiece(@Path("objectId") objectId: Int): ApiArtpiece
}

// helper function
fun ArtpieceApiService.getArtpiecesAsFlow(departmentId: Int): Flow<ApiArtpieceItem> = flow {
    try {
        emit(getArtpieces(departmentId))
    }
    catch(e: Exception){
        Log.e("API", "getArtpiecesAsFlow: "+e.stackTraceToString(), )
    }
}