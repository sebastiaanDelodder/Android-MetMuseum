package com.example.metmuseum.network.items

import kotlinx.serialization.Serializable

@Serializable
data class ApiConstituent (
    val constituentID: Int,
    val role: String,
    val name: String,
    val constituentULAN_URL: String,
    val constituentWikidata_URL: String,
    val gender: String
)
