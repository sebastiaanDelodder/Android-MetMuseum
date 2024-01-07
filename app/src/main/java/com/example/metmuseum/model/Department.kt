package com.example.metmuseum.model

/**
 * Data class representing a Department entity.
 *
 * @property departmentId The unique identifier for the department.
 * @property displayName The display name of the department.
 */
data class Department (
    val departmentId: Int,
    val displayName: String,
)