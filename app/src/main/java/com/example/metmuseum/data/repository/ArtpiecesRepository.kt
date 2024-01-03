package com.example.metmuseum.data.repository

import android.util.Log
import com.example.metmuseum.data.database.artpieces.ArtpieceDao
import com.example.metmuseum.data.database.artpieces.asDbArtpiece
import com.example.metmuseum.data.database.artpieces.asDomainArtpiece
import com.example.metmuseum.data.database.artpieces.asDomainArtpieces
import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.ArtpieceApiService
import com.example.metmuseum.network.DepartmentApiService
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.network.getArtpiecesAsFlow
import com.example.metmuseum.network.getDepartmentsAsFlow
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
    fun getArtpieces(): Flow<List<Artpiece>>

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
    suspend fun refresh()
}

class CachingArtpiecesRepository(
    private val artpieceDao: ArtpieceDao,
    private val artpieceApiService: ArtpieceApiService
): ArtpiecesRepository {

    //this repo contains logic to refresh the departments (remote)
    //sometimes that logic is written in a 'usecase'
    override fun getArtpieces(): Flow<List<Artpiece>> {
        return artpieceDao.getAllArtpieces().map {
            it.asDomainArtpieces()
        }.onEach {
            //todo: check when refresh is called (why duplicates??)
            if(it.isEmpty()){
                refresh()
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

    override suspend fun refresh(){
        try {
            artpieceApiService.getArtpiecesAsFlow().asDomainObjects().collect {
                    value ->
                Log.i("CachingArtpiecesRepository", "refresh: $value")
                for(artpiece in value) {
                    Log.i("CachingArtpiecesRepository", "refresh: $artpiece")
                    insertArtpiece(artpiece)
                }
            }
        }
        catch(e: SocketTimeoutException){
            //log something
            //TODO
            Log.e("API", "refresh: "+e.stackTraceToString(), )
        }

    }
}