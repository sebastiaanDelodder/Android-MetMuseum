package com.example.metmuseum.network

import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.items.ApiConstituent
import com.example.metmuseum.network.items.ApiMeasurement
import com.example.metmuseum.network.items.ApiTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ApiArtpiece(
    val objectID: Int,
    val isHighlight: Boolean,
    val accessionNumber: String,
    val accessionYear: String,
    val isPublicDomain: Boolean,
    val primaryImage: String,
    val primaryImageSmall: String,
    val additionalImages: List<String>,
    val constituents: List<ApiConstituent>?,
    val department: String,
    val objectName: String,
    val title: String,
    val culture: String,
    val period: String,
    val dynasty: String,
    val reign: String,
    val portfolio: String,
    val artistRole: String,
    val artistPrefix: String,
    val artistDisplayName: String,
    val artistDisplayBio: String,
    val artistSuffix: String,
    val artistAlphaSort: String,
    val artistNationality: String,
    val artistBeginDate: String,
    val artistEndDate: String,
    val artistGender: String,
    val artistWikidata_URL: String,
    val artistULAN_URL: String,
    val objectDate: String,
    val objectBeginDate: Int,
    val objectEndDate: Int,
    val medium: String,
    val dimensions: String,
    val measurements: List<ApiMeasurement>?,
    val creditLine: String,
    val geographyType: String,
    val city: String,
    val state: String,
    val county: String,
    val country: String,
    val region: String,
    val subregion: String,
    val locale: String,
    val locus: String,
    val excavation: String,
    val river: String,
    val classification: String,
    val rightsAndReproduction: String,
    val linkResource: String,
    val metadataDate: String,
    val repository: String,
    val objectURL: String,
    val tags: List<ApiTag>?,
    val objectWikidata_URL: String,
    val isTimelineWork: Boolean,
    val GalleryNumber: String,
) {
}

fun Flow<ApiArtpiece>.asDomainObject(): Flow<Artpiece> {
    return map {
        it.asDomainObject()
    }
}

fun ApiArtpiece.asDomainObject(): Artpiece {
    return Artpiece(
        objectID = this.objectID,
        isPublicDomain = this.isPublicDomain,
        primaryImage = this.primaryImage,
        primaryImageSmall = this.primaryImageSmall,
        title = this.title
    )
}