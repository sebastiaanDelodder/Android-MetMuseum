package com.example.metmuseum.data.database.departments

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class dbDepartment (
    @PrimaryKey(autoGenerate = true)
    val departmentId: Int = 0,
    val displayName: String = "",
)