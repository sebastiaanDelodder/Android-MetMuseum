package com.example.metmuseum.data.database.departments

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.metmuseum.model.Department

@Entity(tableName = "departments")
data class dbDepartment (
    @PrimaryKey(autoGenerate = true)
    val departmentId: Int = 0,
    val displayName: String = "",
)

fun dbDepartment.asDomainDepartment(): Department {
    return Department(
        departmentId = this.departmentId,
        displayName = this.displayName
    )
}

fun Department.asDbDepartment(): dbDepartment {
    return dbDepartment(
        departmentId = this.departmentId,
        displayName = this.displayName
    )
}

fun List<dbDepartment>.asDomainDepartments(): List<Department>{
    var departmentList = this.map {
        Department(
            departmentId = it.departmentId,
            displayName = it.displayName
        )
    }
    return departmentList
}