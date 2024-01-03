package com.example.metmuseum.network.items

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ApiMeasurement (
    val elementName: String,
    val elementDescription: String?,
    val elementMeasurements: ApiElementMeasurements
)

@Serializable
data class ApiElementMeasurements (
    val Height: Double? = null,
    val Width: Double? = null,
    val Diameter: Double? = null,
    val Depth: Double? = null,
    val Weight: Double? = null,
) {}
