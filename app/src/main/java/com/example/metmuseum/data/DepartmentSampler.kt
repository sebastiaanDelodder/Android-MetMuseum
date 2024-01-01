package com.example.metmuseum.data

import android.util.Log
import com.example.metmuseum.model.Department

object DepartmentSampler {
    val sampleDepartments = listOf(
            Department(1, "American Decorative Arts"),
            Department(2, "Ancient Near Eastern Art"),
            Department(3, "Arms and Armor"),
            Department(4, "Arts of Africa, Oceania, and the Americas"),
            Department(5, "Asian Art"),
            Department(6, "The Cloisters"),
            Department(7, "The Costume Institute"),
            Department(8, "Drawings and Prints"),
            Department(9, "Egyptian Art"),
            Department(10, "European Paintings"),
            Department(11, "European Sculpture and Decorative Arts"),
            Department(12, "Greek and Roman Art"),
            Department(13, "Islamic Art"),
            Department(14, "The Robert Lehman Collection"),
            Department(15, "The Libraries"),
            Department(16, "Medieval Art"),
            )

    val getAll: () -> List<Department> = {
        Log.d("DepartmentSampler", "getAll called")
        sampleDepartments
    }
}