package com.example.metmuseum.network.services

import android.util.Log
import com.example.metmuseum.network.ApiArtpiece
import com.example.metmuseum.network.items.ApiArtpieceItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface defining API calls related to Artpieces.
 *
 * This interface provides methods for fetching Artpieces from a remote API.
 */
interface ArtpieceApiService {

    /**
     * Fetches a list of Artpieces based on the provided departmentId.
     *
     * @param departmentId The departmentId for filtering Artpieces.
     * @return An [ApiArtpieceItem] representing the response with a list of objectIDs.
     */
    @GET("objects")
    suspend fun getArtpieces(@Query("departmentIds") departmentId: Int): ApiArtpieceItem

    /**
     * Fetches details of a specific Artpiece based on the provided objectId.
     *
     * @param objectId The objectId of the Artpiece to retrieve details for.
     * @return An [ApiArtpiece] representing the response with details of the specified Artpiece.
     */
    @GET("objects/{objectId}")
    suspend fun getArtpiece(@Path("objectId") objectId: Int): ApiArtpiece
}

/**
 * Helper function to fetch Artpieces and emit their objectIDs as a [Flow].
 *
 * This function wraps the synchronous API call in a flow and emits the list of artpiece objectIDs.
 * In case of an exception during the API call, an error message is logged.
 *
 * @param departmentId The departmentId for filtering Artpieces.
 * @return A [Flow] emitting a list of objectIDs for the fetched Artpieces.
 */
fun ArtpieceApiService.getArtpiecesAsFlow(departmentId: Int): Flow<List<Int>> = flow {
    try {
        Log.i("ArtpieceApiService", "getArtpiecesAsFlow: departmentId = $departmentId")
        emit(getArtpieces(departmentId).objectIDs)
    }
    catch(e: Exception){
        Log.e("ArtpieceApiService", e.stackTraceToString())
    }
}

/**
 * Helper function to fetch details of a specific Artpiece and emit it as a [Flow].
 *
 * This function wraps the synchronous API call in a flow and emits the list of artpieces.
 * In case of an exception during the API call, an error message is logged.
 *
 * @param objectId The objectId of the Artpiece to retrieve details for.
 * @return A [Flow] emitting details of the specified Artpiece.
 */
fun ArtpieceApiService.getArtpieceAsFlow(objectId: Int): Flow<ApiArtpiece> = flow {
    try {
        Log.i("ArtpieceApiService", "getArtpieceAsFlow: objectId = $objectId")
        emit(getArtpiece(objectId))
    }
    catch(e: Exception){
        Log.e("ArtpieceApiService", e.stackTraceToString())
    }
}