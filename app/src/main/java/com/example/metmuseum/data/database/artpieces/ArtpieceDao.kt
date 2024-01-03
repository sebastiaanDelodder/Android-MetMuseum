package com.example.metmuseum.data.database.artpieces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtpieceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbArtpiece)

    @Update
    suspend fun update(item: dbArtpiece)

    @Delete
    suspend fun delete(item: dbArtpiece)

    @Query("SELECT * from artpieces WHERE objectID = :id")
    fun getArtpiece(id: Int): Flow<dbArtpiece>

    @Query("SELECT * from artpieces ORDER BY title ASC")
    fun getAllArtpieces(): Flow<List<dbArtpiece>>
}