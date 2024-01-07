package com.example.metmuseum.data.database.artpieces

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.metmuseum.model.Artpiece

/**
 * Entity class representing an [Artpiece] in the database.
 *
 * @property objectID Unique identifier for the Artpiece.
 * @property primaryImageSmall URL to the small-sized primary image of the Artpiece.
 * @property title Title of the Artpiece.
 * @property department Department to which the Artpiece belongs.
 * @property artistDisplayName Display name of the artist.
 * @property artistNationality Nationality of the artist.
 * @property artistBeginDate Begin date of the artist.
 * @property artistEndDate End date of the artist.
 * @property objectDate Date associated with the Artpiece.
 * @property medium Medium used in creating the Artpiece.
 * @property dimensions Dimensions of the Artpiece.
 * @property country Country associated with the Artpiece.
 * @property culture Cultural origin of the Artpiece.
 * @property period Period to which the Artpiece belongs.
 * @property dynasty Dynasty associated with the Artpiece.
 * @property publicDomain Indicates whether the Artpiece is in the public domain.
 * @property galleryNumber Gallery number associated with the Artpiece.
 *
 * @constructor Creates a new [dbArtpiece] with default values.
 */
@Entity(tableName = "artpieces")
data class dbArtpiece (
    @PrimaryKey(autoGenerate = true)
    val objectID: Int = 0,
    var primaryImageSmall: String = "",
    var title: String = "",
    var department: String = "",
    var artistDisplayName: String = "",
    var artistNationality: String = "",
    var artistBeginDate: String = "",
    var artistEndDate: String = "",
    var objectDate: String = "",
    var medium: String = "",
    var dimensions: String = "",
    var country: String = "",
    var culture: String = "",
    var period: String = "",
    var dynasty: String = "",
    var publicDomain: Boolean = false,
    var galleryNumber: String = "",
)

/**
 * Converts a [dbArtpiece] to a corresponding [Artpiece] in the domain layer.
 *
 * @return [Artpiece] representation of the [dbArtpiece].
 */
fun dbArtpiece.asDomainArtpiece(): Artpiece {
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
        publicDomain = this.publicDomain,
        galleryNumber = this.galleryNumber
    )
}

/**
 * Converts an [Artpiece] from the domain layer to a corresponding [dbArtpiece] in the database.
 *
 * @return [dbArtpiece] representation of the [Artpiece].
 */
fun Artpiece.asDbArtpiece(): dbArtpiece {
    return dbArtpiece(
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
        publicDomain = this.publicDomain,
        galleryNumber = this.galleryNumber,
    )
}

/**
 * Converts a list of [dbArtpiece] instances to a list of corresponding [Artpiece] instances in the domain layer.
 *
 * @return List of [Artpiece] representations of the [dbArtpiece] instances.
 */
fun List<dbArtpiece>.asDomainArtpieces(): List<Artpiece>{
    var artpieceList = this.map {
        Artpiece(
            objectID = it.objectID,
            primaryImageSmall = it.primaryImageSmall,
            title = it.title,
            department = it.department,
            artistDisplayName = it.artistDisplayName,
            artistNationality = it.artistNationality,
            artistBeginDate = it.artistBeginDate,
            artistEndDate = it.artistEndDate,
            objectDate = it.objectDate,
            medium = it.medium,
            dimensions = it.dimensions,
            country = it.country,
            culture = it.culture,
            period = it.period,
            dynasty = it.dynasty,
            publicDomain = it.publicDomain,
            galleryNumber = it.galleryNumber
        )
    }
    return artpieceList
}