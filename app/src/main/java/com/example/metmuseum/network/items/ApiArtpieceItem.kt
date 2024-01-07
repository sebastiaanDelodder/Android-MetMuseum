package com.example.metmuseum.network.items

import kotlinx.serialization.Serializable

/**
 * Serializable data class representing an item retrieved from the Art API.
 *
 * Due to the way the API is structured, the Art API returns a list of object IDs for a given department.
 *
 * @property total The total number of items.
 * @property objectIDs The list of object IDs associated with the items.
 *
 * @see Serializable
 */
@Serializable
data class ApiArtpieceItem (
    val total: Int,
    val objectIDs: List<Int>
)

