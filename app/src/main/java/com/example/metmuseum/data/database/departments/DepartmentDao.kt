package com.example.metmuseum.data.database.departments

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for the [dbDepartment] entity.
 *
 * The DAO provides methods to interact with the underlying database and perform CRUD operations on [dbDepartment].
 */
@Dao
interface DepartmentDao {
    /**
     * Inserts a new [dbDepartment] into the database. If the item already exists, it is replaced.
     *
     * @param item The [dbDepartment] to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbDepartment)

    /**
     * Updates an existing [dbDepartment] in the database.
     *
     * @param item The [dbDepartment] to be updated.
     */
    @Update
    suspend fun update(item: dbDepartment)

    /**
     * Deletes a [dbDepartment] from the database.
     *
     * @param item The [dbDepartment] to be deleted.
     */
    @Delete
    suspend fun delete(item: dbDepartment)

    /**
     * Retrieves a [dbDepartment] entity from the database based on its departmentId.
     *
     * @param id The departmentId of the Department entity to retrieve.
     * @return A [Flow] emitting the [dbDepartment] entity with the specified departmentId, or null if not found.
     */
    @Query("SELECT * from departments WHERE departmentId = :id")
    fun getDepartment(id: Int): Flow<dbDepartment>

    /**
     * Retrieves all [dbDepartment] entities from the database, ordered by their display names in ascending order.
     *
     * @return A [Flow] emitting a list of all [dbDepartment] entities.
     */
    @Query("SELECT * from departments ORDER BY displayName ASC")
    fun getAllDepartments(): Flow<List<dbDepartment>>
}