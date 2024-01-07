package com.example.metmuseum.network.items

import kotlinx.serialization.Serializable

/**
 * Serializable data class representing a constituent obtained from an API response.
 *
 * This data class encapsulates information about a constituent, including their ID, role, name,
 * ULAN URL, Wikidata URL, and gender.
 *
 * @property constituentID The unique identifier for the constituent.
 * @property role The role of the constituent.
 * @property name The name of the constituent.
 * @property constituentULAN_URL The ULAN (Union List of Artist Names) URL associated with the constituent.
 * @property constituentWikidata_URL The Wikidata URL associated with the constituent.
 * @property gender The gender of the constituent.
 *
 * @see Serializable
 */
@Serializable
data class ApiConstituent (
    val constituentID: Int,
    val role: String,
    val name: String,
    val constituentULAN_URL: String,
    val constituentWikidata_URL: String,
    val gender: String
)
