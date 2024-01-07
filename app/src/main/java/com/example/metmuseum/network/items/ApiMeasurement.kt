package com.example.metmuseum.network.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing a measurement for an element, including its name, description, and specific measurements.
 *
 * @property elementName The name of the element.
 * @property elementDescription The description of the element (nullable).
 * @property elementMeasurements The specific measurements for the element.
 *
 * @see Serializable
 */
@Serializable
data class ApiMeasurement (
    val elementName: String,
    val elementDescription: String?,
    val elementMeasurements: ApiElementMeasurements
)

/**
 * Serializable data class representing specific measurements for an element, including height, width, diameter, depth, weight, length,
 * thickness, circumference, rim, and a special measurement named "Length at CB".
 *
 * @property Height The height of the element (nullable).
 * @property Width The width of the element (nullable).
 * @property Diameter The diameter of the element (nullable).
 * @property Depth The depth of the element (nullable).
 * @property Weight The weight of the element (nullable).
 * @property Length The length of the element (nullable).
 * @property Thickness The thickness of the element (nullable).
 * @property Circumference The circumference of the element (nullable).
 * @property Rim The rim measurement of the element (nullable).
 * @property CB The "Length at CB" measurement of the element (nullable).
 *
 * @see Serializable
 */
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
)
