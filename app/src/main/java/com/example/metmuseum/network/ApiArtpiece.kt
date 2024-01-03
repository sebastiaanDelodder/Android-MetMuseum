package com.example.metmuseum.network

import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiArtpiece(
    val objectID: Int,
    val isPublicDomain: Boolean,
    val primaryImage: String,
    val primaryImageSmall: String,
    val title: String
) {}

fun Flow<List<ApiArtpiece>>.asDomainObjects(): Flow<List<Artpiece>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiArtpiece>.asDomainObjects(): List<Artpiece> {
    var domainList = this.map {
        Artpiece(
            objectID = it.objectID,
            isPublicDomain = it.isPublicDomain,
            primaryImage = it.primaryImage,
            primaryImageSmall = it.primaryImageSmall,
            title = it.title
        )
    }
    return domainList
}