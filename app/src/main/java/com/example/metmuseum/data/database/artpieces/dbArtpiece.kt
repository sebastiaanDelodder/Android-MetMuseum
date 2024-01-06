package com.example.metmuseum.data.database.artpieces

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.metmuseum.model.Artpiece

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