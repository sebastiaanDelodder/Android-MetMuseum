package com.example.metmuseum.network

import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.network.items.ApiConstituent
import com.example.metmuseum.network.items.ApiMeasurement
import com.example.metmuseum.network.items.ApiTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

/**
 * Represents an art piece retrieved from an API.
 *
 * This class is annotated with [Serializable] to support serialization/deserialization.
 *
 * @property objectID The unique identifier for the art piece.
 * @property isHighlight Indicates whether the art piece is a highlight.
 * @property accessionNumber The accession number of the art piece.
 * @property accessionYear The year the art piece was accessioned.
 * @property isPublicDomain Indicates whether the art piece is in the public domain.
 * @property primaryImage URL of the primary image of the art piece.
 * @property primaryImageSmall URL of a small version of the primary image.
 * @property additionalImages List of URLs for additional images of the art piece.
 * @property constituents List of constituents (contributors) associated with the art piece.
 * @property department The department to which the art piece belongs.
 * @property objectName The type or name of the art object.
 * @property title The title of the art piece.
 * @property culture The cultural context of the art piece.
 * @property period The historical period of the art piece.
 * @property dynasty The dynasty associated with the art piece.
 * @property reign The reign during which the art piece was created.
 * @property portfolio The portfolio to which the art piece belongs.
 * @property artistRole The role of the artist.
 * @property artistPrefix The prefix associated with the artist's name.
 * @property artistDisplayName The displayed name of the artist.
 * @property artistDisplayBio The biography of the artist.
 * @property artistSuffix The suffix associated with the artist's name.
 * @property artistAlphaSort The alpha-sorted name of the artist.
 * @property artistNationality The nationality of the artist.
 * @property artistBeginDate The birth date of the artist.
 * @property artistEndDate The death date of the artist.
 * @property artistGender The gender of the artist.
 * @property artistWikidata_URL The Wikidata URL of the artist.
 * @property artistULAN_URL The ULAN (Getty Vocabulary Program) URL of the artist.
 * @property objectDate The date associated with the art piece.
 * @property objectBeginDate The start date of the art piece.
 * @property objectEndDate The end date of the art piece.
 * @property medium The artistic medium used for the art piece.
 * @property dimensions The textual description of the art piece's dimensions.
 * @property measurements List of measurements associated with the art piece.
 * @property creditLine The credit line for the art piece.
 * @property geographyType The type of geography associated with the art piece.
 * @property city The city associated with the art piece.
 * @property state The state associated with the art piece.
 * @property county The county associated with the art piece.
 * @property country The country associated with the art piece.
 * @property region The region associated with the art piece.
 * @property subregion The subregion associated with the art piece.
 * @property locale The locale associated with the art piece.
 * @property locus The locus associated with the art piece.
 * @property excavation The excavation information associated with the art piece.
 * @property river The river associated with the art piece.
 * @property classification The classification of the art piece.
 * @property rightsAndReproduction Information about rights and reproduction for the art piece.
 * @property linkResource URL linking to additional resources related to the art piece.
 * @property metadataDate The date of the metadata associated with the art piece.
 * @property repository The repository or institution where the art piece is stored.
 * @property objectURL URL linking to the detailed information about the art piece.
 * @property tags List of tags associated with the art piece.
 * @property objectWikidata_URL The Wikidata URL associated with the art piece.
 * @property isTimelineWork Indicates whether the art piece is a part of the timeline.
 * @property GalleryNumber The gallery number associated with the art piece.
 *
 * @see Serializable
 */
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
)

/**
 * Extension function on Flow<ApiArtpiece> that transforms each element into its corresponding domain model [Artpiece].
 *
 * This function maps each element of the flow using the [asDomainObject] extension function on [ApiArtpiece].
 *
 * @return A [Flow] emitting the transformed domain model [Artpiece] elements.
 */
fun Flow<ApiArtpiece>.asDomainObject(): Flow<Artpiece> {
    return map {
        it.asDomainObject()
    }
}

/**
 * Extension function to convert an [ApiArtpiece] object to a domain model [Artpiece].
 *
 * @return A new [Artpiece] instance with the converted data from the [ApiArtpiece].
 */
fun ApiArtpiece.asDomainObject(): Artpiece {
    return Artpiece(
        objectID = this.objectID,
        primaryImageSmall = this.primaryImageSmall,
        title = this.title,
        department = this.department,
        artistDisplayName = this.artistDisplayName,
        artistNationality = this.artistNationality,
        artistBeginDate = this.artistBeginDate,
        artistEndDate = this.artistEndDate,
        objectDate = this.objectDate,
        medium = this.medium,
        dimensions = this.dimensions,
        country = this.country,
        culture = this.culture,
        period = this.period,
        dynasty = this.dynasty,
        publicDomain = this.isPublicDomain,
        galleryNumber = this.GalleryNumber,
    )
}