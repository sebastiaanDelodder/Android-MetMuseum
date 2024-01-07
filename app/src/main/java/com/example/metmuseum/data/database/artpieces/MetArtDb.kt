package com.example.metmuseum.data.database.artpieces

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.dbDepartment

/**
 * RoomDatabase class for managing the Artpiece database using Room persistence library.
 *
 * This database contains a single table for Artpiece entities.
 *
 * @constructor Creates a new empty Artpiece database.
 *
 * @property artpieceDao Provides methods for accessing the ArtpieceDao.
 *
 * @see RoomDatabase
 */
@Database(entities = [dbArtpiece::class, dbDepartment::class], version = 1, exportSchema = false)
abstract class MetArtDb: RoomDatabase(){
    /**
     * Provides methods for accessing the [ArtpieceDao].
     *
     * @return ArtpieceDao instance
     */
    abstract fun artpieceDao(): ArtpieceDao

    abstract fun departmentDao(): DepartmentDao

    companion object {
        @Volatile
        private var Instance: MetArtDb? = null

        fun getDatabase(context: Context): MetArtDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MetArtDb::class.java, "artpiece_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}