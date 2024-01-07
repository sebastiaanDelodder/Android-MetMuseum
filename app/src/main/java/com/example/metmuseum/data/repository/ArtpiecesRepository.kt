package com.example.metmuseum.data.repository

import android.util.Log
import com.example.metmuseum.data.database.artpieces.ArtpieceDao
import com.example.metmuseum.data.database.artpieces.asDbArtpiece
import com.example.metmuseum.data.database.artpieces.asDomainArtpiece
import com.example.metmuseum.data.database.artpieces.asDomainArtpieces
import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.asDomainObject
import com.example.metmuseum.network.services.ArtpieceApiService
import com.example.metmuseum.network.services.getArtpieceAsFlow
import com.example.metmuseum.network.services.getArtpiecesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

/**
 * Repository interface for managing [Artpiece] entities.
 *
 * This interface defines methods for retrieving, inserting, updating, deleting, and refreshing Artpieces.
 */
interface ArtpiecesRepository {
    /**
     * Retrieve all Artpieces from the given data source for the specified department.
     *
     * @param department The [Department] for which to retrieve Artpieces.
     * @return A [Flow] emitting a list of [Artpiece] objects for the specified department.
     */
    fun getArtpieces(department: Department): Flow<List<Artpiece>>

    /**
     * Retrieve an [Artpiece] from the given data source that matches the specified [id].
     *
     * @param id The unique identifier of the [Artpiece] to retrieve.
     * @return A [Flow] emitting the [Artpiece] with the specified [id], or null if not found.
     */
    fun getArtpiece(id: Int): Flow<Artpiece?>

    /**
     * Insert a new [Artpiece] into the local database.
     *
     * @param artpiece The Artpiece to be inserted.
     */
    suspend fun insertArtpiece(artpiece: Artpiece)

    /**
     * Delete an [Artpiece] from the local database.
     *
     * @param artpiece The Artpiece to be deleted.
     */
    suspend fun deleteArtpiece(artpiece: Artpiece)

    /**
     * Update an existing [Artpiece] in the local database.
     *
     * @param artpiece The Artpiece to be updated.
     */
    suspend fun updateArtpiece(artpiece: Artpiece)

    /**
     * Refresh the Artpiece object identifiers stored in the data source for the specified department.
     *
     * @param departmentId The unique identifier of the [Department] for which to refresh Artpieces.
     * @return A [Flow] emitting a list of unique identifiers of refreshed Artpieces.
     */
    suspend fun refresh(departmentId : Int) : Flow<List<Int>>

    /**
     * Refresh a specific [Artpiece] stored in the data source.
     *
     * @param objectId The unique identifier of the Artpiece to refresh.
     */
    suspend fun refreshArtPiece(objectId : Int)
}

/**
 * Implementation of the [ArtpiecesRepository] interface with caching functionality.
 *
 * This class uses a local database access object ([ArtpieceDao]) and a remote API service ([ArtpieceApiService]) to manage Artpieces.
 *
 * @property artpieceDao Local database access object for Artpieces.
 * @property artpieceApiService Remote API service for Artpieces.
 */
class CachingArtpiecesRepository(
    private val artpieceDao: ArtpieceDao,
    private val artpieceApiService: ArtpieceApiService
): ArtpiecesRepository {

    /**
     * Retrieve all Artpieces from the given data source for the specified department.
     *
     * The department's display name is optionally corrected based on predefined mappings.
     * This is due conflicting names stored in the original data source.
     * The flow is observed for changes, and if no Artpieces are cached, a refresh is triggered.
     *
     * @param department The [Department] for which Artpieces are requested.
     * @return A [Flow] emitting a list of [Artpiece] objects for the specified department.
     */
    override fun getArtpieces(department: Department): Flow<List<Artpiece>> {
        Log.i("CachingArtpiecesRepo", "getArtpieces for: $department")
        var stringCorrection = if (department.displayName == "American Decorative Arts") {
            "The American Wing"
        } else if (department.displayName == "Arts of Africa, Oceania, and the Americas") {
            "The Michael C. Rockefeller Wing"
        } else if (department.displayName == "The Costume Institute") {
            "Costume Institute"
        } else if (department.displayName == "The Robert Lehman Collection") {
            "Robert Lehman Collection"
        } else if (department.displayName == "Modern Art"){
            "Modern and Contemporary Art"
        } else {
            department.displayName
        }

        return artpieceDao.getAllArtpieces(stringCorrection).map {
            it.asDomainArtpieces()
        }.onEach {
            if (it.isEmpty()) {
                refresh(department.departmentId)
            }
        }
    }

    /**
     * Retrieve an [Artpiece] from the given data source that matches the specified [id].
     *
     * @param id The ID of the [Artpiece] to retrieve.
     * @return A [Flow] emitting the [Artpiece] with the specified ID, or null if not found.
     */
    override fun getArtpiece(id: Int): Flow<Artpiece?> {
        return artpieceDao.getArtpiece(id).map {
            it.asDomainArtpiece()
        }
    }

    /**
     * Inserts a new [Artpiece] into the local database.
     *
     * @param artpiece The [Artpiece] to be inserted.
     */
    override suspend fun insertArtpiece(artpiece: Artpiece) {
        artpieceDao.insert(artpiece.asDbArtpiece())
    }

    /**
     * Deletes an [Artpiece] from the local database.
     *
     * @param artpiece The [Artpiece] to be deleted.
     */
    override suspend fun deleteArtpiece(artpiece: Artpiece) {
        artpieceDao.delete(artpiece.asDbArtpiece())
    }

    /**
     * Updates an existing [Artpiece] in the local database.
     *
     * @param artpiece The [Artpiece] to be updated.
     */
    override suspend fun updateArtpiece(artpiece: Artpiece) {
        artpieceDao.update(artpiece.asDbArtpiece())
    }

    /**
     * Initiates a refresh of Artpieces data for a specific department.
     *
     * The refresh is performed by querying a remote API for Artpieces related to the specified department.
     *
     * @param departmentId The ID of the [Department] to refresh.
     * @return A [Flow] emitting a list of IDs for the refreshed Artpieces.
     */
    override suspend fun refresh(departmentId: Int): Flow<List<Int>> {
        try {
            Log.i("CachingArtpiecesRepository", "Refresh: departmentId = $departmentId")
            return artpieceApiService.getArtpiecesAsFlow(departmentId)
        } catch (e: SocketTimeoutException) {
            Log.e("CachingArtpiecesRepository", "Refresh Error: " + e.stackTraceToString(),)
            throw e
        } catch (e: Exception) {
            Log.e("CachingArtpiecesRepository", "Refresh Error: " + e.stackTraceToString(),)
            throw e
        }
    }

    /**
     * Initiates a refresh of a specific [Artpiece] data.
     *
     * The refresh is performed by querying a remote API for the specific Artpiece with the given object ID.
     *
     * @param objectId The object ID of the [Artpiece] to refresh.
     */
    override suspend fun refreshArtPiece(objectId: Int) {
        try {
            artpieceApiService.getArtpieceAsFlow(objectId).asDomainObject().collect { value ->
                Log.i("CachingArtpiecesRepository", "Refresh: Artpiece $value")
                insertArtpiece(value)
            }
        } catch (e: SocketTimeoutException) {
            Log.e("CachingArtpiecesRepository", "RefreshArtpiece: Error: " + e.stackTraceToString(),)
        }
    }
}