package com.example.metmuseum.data.database.artpieces

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department

@Entity(tableName = "artpieces")
data class dbArtpiece (
    @PrimaryKey(autoGenerate = true)
    val objectID: Int = 0,
    var isPublicDomain: Boolean = false,
    var primaryImage: String = "",
    var primaryImageSmall: String = "",
    var title: String = "",
)

fun dbArtpiece.asDomainArtpiece(): Artpiece {
    return Artpiece(
        objectID = this.objectID,
        isPublicDomain = this.isPublicDomain,
        primaryImage = this.primaryImage,
        primaryImageSmall = this.primaryImageSmall,
        title = this.title
    )
}

fun Artpiece.asDbArtpiece(): dbArtpiece {
    return dbArtpiece(
        objectID = this.objectID,
        isPublicDomain = this.isPublicDomain,
        primaryImage = this.primaryImage,
        primaryImageSmall = this.primaryImageSmall,
        title = this.title
    )
}

fun List<dbArtpiece>.asDomainArtpieces(): List<Artpiece>{
    var artpieceList = this.map {
        Artpiece(
            objectID = it.objectID,
            isPublicDomain = it.isPublicDomain,
            primaryImage = it.primaryImage,
            primaryImageSmall = it.primaryImageSmall,
            title = it.title
        )
    }
    return artpieceList
}