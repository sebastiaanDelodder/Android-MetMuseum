package com.example.metmuseum.data.database.departments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [dbDepartment::class], version = 1, exportSchema = false)
abstract class DepartmentDb: RoomDatabase(){
    abstract fun departmentDao(): DepartmentDao

    companion object {
        @Volatile
        private var Instance: DepartmentDb? = null

        fun getDatabase(context: Context): DepartmentDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DepartmentDb::class.java, "department_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}