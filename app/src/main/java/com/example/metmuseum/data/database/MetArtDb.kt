package com.example.metmuseum.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.metmuseum.data.database.artpieces.ArtpieceDao
import com.example.metmuseum.data.database.artpieces.dbArtpiece
import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.dbDepartment

/**
 * RoomDatabase class for managing the MetArt database using Room persistence library.
 *
 * This database contains two tables: one for Artpiece entities and another for Department entities.
 *
 * @constructor Create an empty MetArt database.
 *
 * @property artpieceDao Provides methods for accessing the [ArtpieceDao].
 * @property departmentDao Provides methods for accessing the [DepartmentDao].
 *
 * @see RoomDatabase
 */
@Database(entities = [dbArtpiece::class, dbDepartment::class], version = 1, exportSchema = false)
abstract class MetArtDb: RoomDatabase(){

    /**
     * Provides methods for accessing the [ArtpieceDao].
     *
     * @return [ArtpieceDao] instance
     */
    abstract fun artpieceDao(): ArtpieceDao

    /**
     * Provides methods for accessing the [DepartmentDao].
     *
     * @return [DepartmentDao] instance
     */
    abstract fun departmentDao(): DepartmentDao

    /**
     * Companion object to provide methods for creating or retrieving the MetArtDb instance.
     */
    companion object {
        @Volatile
        private var Instance: MetArtDb? = null

        /**
         * Gets the MetArtDb instance. If the instance is not null, returns it.
         * Otherwise, creates a new database instance.
         *
         * @param context Application context
         * @return [MetArtDb] instance
         */
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