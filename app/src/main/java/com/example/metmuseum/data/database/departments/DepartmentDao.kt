package com.example.metmuseum.data.database.departments

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbDepartment)

    @Update
    suspend fun update(item: dbDepartment)

    @Delete
    suspend fun delete(item: dbDepartment)

    @Query("SELECT * from departments WHERE departmentId = :id")
    fun getDepartment(id: Int): Flow<dbDepartment>

    @Query("SELECT * from departments ORDER BY displayName ASC")
    fun getAllDepartments(): Flow<List<dbDepartment>>
}