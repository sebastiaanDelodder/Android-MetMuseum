package com.example.metmuseum.data.database.departments

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.metmuseum.model.Department

/**
 * Entity class representing a [Department] in the database.
 *
 * @property departmentId Unique identifier for the department. Auto-generated if not provided.
 * @property displayName Display name of the department.
 */
@Entity(tableName = "departments")
data class dbDepartment (
    @PrimaryKey(autoGenerate = true)
    val departmentId: Int = 0,
    val displayName: String = "",
)

/**
 * Extension function to convert a [dbDepartment] entity to a domain [Department] model.
 *
 * @return Equivalent [Department] model.
 */
fun dbDepartment.asDomainDepartment(): Department {
    return Department(
        departmentId = this.departmentId,
        displayName = this.displayName
    )
}

/**
 * Extension function to convert a [Department] domain model to a [dbDepartment] entity.
 *
 * @return Equivalent [dbDepartment] entity.
 */
fun Department.asDbDepartment(): dbDepartment {
    return dbDepartment(
        departmentId = this.departmentId,
        displayName = this.displayName
    )
}

/**
 * Extension function to convert a list of [dbDepartment] entities to a list of [Department] domain models.
 *
 * @return List of equivalent [Department] models.
 */
fun List<dbDepartment>.asDomainDepartments(): List<Department>{
    var departmentList = this.map {
        Department(
            departmentId = it.departmentId,
            displayName = it.displayName
        )
    }
    return departmentList
}