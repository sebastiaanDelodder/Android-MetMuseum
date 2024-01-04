package com.example.metmuseum.network.items

import kotlinx.serialization.Serializable

@Serializable
data class ApiTag (
    val term: String,
    val AAT_URL: String?,
    val Wikidata_URL: String?,
)
