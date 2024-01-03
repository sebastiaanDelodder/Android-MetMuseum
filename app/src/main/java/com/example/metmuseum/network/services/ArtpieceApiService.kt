package com.example.metmuseum.network.services

import android.util.Log
import com.example.metmuseum.network.ApiArtpiece
import com.example.metmuseum.network.items.ApiArtpieceItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
fun ArtpieceApiService.getArtpiecesAsFlow(departmentId: Int): Flow<List<Int>> = flow {
    try {
        emit(getArtpieces(departmentId).objectIDs)
    }
    catch(e: Exception){
        Log.e("ArtpieceApiService", "getArtpiecesAsFlow: Could not connect to host")
    }
}

fun ArtpieceApiService.getArtpieceAsFlow(objectId: Int): Flow<ApiArtpiece> = flow {
    try {
        emit(getArtpiece(objectId))
    }
    catch(e: Exception){
        Log.e("ArtpieceApiService", "getArtpieceAsFlow: Could not connect to host")
    }
}