package com.example.metmuseum.network.items

import kotlinx.serialization.Serializable

/**
 * Serializable data class representing a tag obtained from an API.
 *
 * This class contains information about a tag, including its term, AAT URL, and Wikidata URL.
 *
 * @property term The term of the tag.
 * @property AAT_URL The AAT (Getty Art & Architecture Thesaurus) URL associated with the tag, or null if not available.
 * @property Wikidata_URL The Wikidata URL associated with the tag, or null if not available.
 *
 * @see Serializable
 */
@Serializable
data class ApiTag (
    val term: String,
    val AAT_URL: String?,
    val Wikidata_URL: String?,
)
