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
 * Repository that provides insert, update, delete, and retrieve of [Artpiece] from a given data source.
 */
interface ArtpiecesRepository {
    /**
     * Retrieve all the artpieces from the the given data source.
     */
    fun getArtpieces(department: Department): Flow<List<Artpiece>>

    /**
     * Retrieve an artpiece from the given data source that matches with the [id].
     */
    fun getArtpiece(id: Int): Flow<Artpiece?>

    /**
     * Insert artpiece in the data source
     */
    suspend fun insertArtpiece(artpiece: Artpiece)

    /**
     * Delete artpiece from the data source
     */
    suspend fun deleteArtpiece(artpiece: Artpiece)

    /**
     * Update artpiece in the data source
     */
    suspend fun updateArtpiece(artpiece: Artpiece)

    /**
     * Refresh the artpieces stored in the data source
     */
    suspend fun refresh(departmentId : Int) : Flow<List<Int>>

    /**
     * Refresh the artpieces stored in the data source
     */
    suspend fun refreshArtPiece(objectId : Int)
}

class CachingArtpiecesRepository(
    private val artpieceDao: ArtpieceDao,
    private val artpieceApiService: ArtpieceApiService
): ArtpiecesRepository {

    //this repo contains logic to refresh the departments (remote)
    //sometimes that logic is written in a 'usecase'
    override fun getArtpieces(department: Department): Flow<List<Artpiece>> {
        //TODO
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
            //Log.i("CachingArtpiecesRepo", "getArtpieces: $it")
            //todo: check when refresh is called (why duplicates??)
            if (it.isEmpty()) {
                refresh(department.departmentId)
            }
        }
    }

    override fun getArtpiece(id: Int): Flow<Artpiece?> {
        return artpieceDao.getArtpiece(id).map {
            it.asDomainArtpiece()
        }
    }

    override suspend fun insertArtpiece(artpiece: Artpiece) {
        artpieceDao.insert(artpiece.asDbArtpiece())
    }

    override suspend fun deleteArtpiece(artpiece: Artpiece) {
        artpieceDao.delete(artpiece.asDbArtpiece())
    }

    override suspend fun updateArtpiece(artpiece: Artpiece) {
        artpieceDao.update(artpiece.asDbArtpiece())
    }

    override suspend fun refresh(departmentId: Int): Flow<List<Int>> {
        try {
            Log.i("CachingArtpiecesRepository", "Refresh: departmentId = $departmentId")
            return artpieceApiService.getArtpiecesAsFlow(departmentId)
        } catch (e: SocketTimeoutException) {
            //log something
            //TODO
            Log.e("CachingArtpiecesRepository", "Refresh: departmentId = $departmentId Error:" + e.stackTraceToString(),)
            throw e
        } catch (e: Exception) {
            Log.e("CachingArtpiecesRepository", "Refresh: departmentId = $departmentId Error:" + e.stackTraceToString(),)
            throw e
        }
    }

    override suspend fun refreshArtPiece(objectId: Int) {
        try {
            artpieceApiService.getArtpieceAsFlow(objectId).asDomainObject().collect { value ->
                Log.i("CachingArtpiecesRepository", "Refresh: Artpiece $value")
                insertArtpiece(value)
            }
        } catch (e: SocketTimeoutException) {
            //log something
            //TODO
            Log.e("CachingArtpiecesRepository", "Refresh: Artpiece Error: " + e.stackTraceToString(),)
        }
    }
}