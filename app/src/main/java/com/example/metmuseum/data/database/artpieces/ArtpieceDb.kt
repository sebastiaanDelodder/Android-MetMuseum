package com.example.metmuseum.data.database.artpieces

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.dbDepartment

@Database(entities = [dbArtpiece::class], version = 1, exportSchema = false)
abstract class ArtpieceDb: RoomDatabase(){
    abstract fun artpieceDao(): ArtpieceDao

    companion object {
        @Volatile
        private var Instance: ArtpieceDb? = null

        fun getDatabase(context: Context): ArtpieceDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ArtpieceDb::class.java, "artpiece_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}