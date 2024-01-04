package com.example.metmuseum.network.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val Length: Double? = null,
    val Thickness: Double? = null,
    val Circumference: Double? = null,
    val Rim: Double? = null,
    @SerialName("Length at CB") val CB: Double? = null,
) {}
