package com.example.metmuseum.data.database.artpieces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for the [dbArtpiece] entity.
 *
 * The DAO provides methods to interact with the underlying database and perform CRUD operations on [dbArtpiece].
 */
@Dao
interface ArtpieceDao {
    /**
     * Inserts a new [dbArtpiece] into the database. If the item already exists, it is replaced.
     *
     * @param item The [dbArtpiece] to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbArtpiece)

    /**
     * Updates an existing [dbArtpiece] in the database.
     *
     * @param item The [dbArtpiece] to be updated.
     */
    @Update
    suspend fun update(item: dbArtpiece)

    /**
     * Deletes a [dbArtpiece] from the database.
     *
     * @param item The [dbArtpiece] to be deleted.
     */
    @Delete
    suspend fun delete(item: dbArtpiece)

    /**
     * Retrieves a specific [dbArtpiece] from the database based on its object ID.
     *
     * @param id The object ID of the desired [dbArtpiece].
     * @return A [Flow] emitting the [dbArtpiece] with the specified object ID.
     */
    @Query("SELECT * from artpieces WHERE objectID = :id")
    fun getArtpiece(id: Int): Flow<dbArtpiece>

    /**
     * Retrieves all [dbArtpiece] items from the database that belong to a specific department.
     *
     * @param departmentName The name of the department.
     * @return A [Flow] emitting a list of [dbArtpiece] items for the specified department, ordered by object ID.
     */
    @Query("SELECT * from artpieces WHERE department == :departmentName ORDER BY objectID ASC")
    fun getAllArtpieces(departmentName: String): Flow<List<dbArtpiece>>
}