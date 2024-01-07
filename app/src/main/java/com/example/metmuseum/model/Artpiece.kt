package com.example.metmuseum.model

/**
 * Data class representing an art piece.
 *
 * @property objectID The unique identifier for the art piece.
 * @property primaryImageSmall URL of the small-sized primary image for the art piece.
 * @property title The title of the art piece.
 * @property department The department to which the art piece belongs.
 * @property artistDisplayName The display name of the artist associated with the art piece.
 * @property artistNationality The nationality of the artist associated with the art piece.
 * @property artistBeginDate The birth date of the artist associated with the art piece.
 * @property artistEndDate The death date of the artist associated with the art piece.
 * @property objectDate The date associated with the creation of the art piece.
 * @property medium The medium used for creating the art piece.
 * @property dimensions The dimensions of the art piece.
 * @property country The country associated with the art piece.
 * @property culture The culture associated with the art piece.
 * @property period The historical period associated with the art piece.
 * @property dynasty The dynasty associated with the art piece.
 * @property publicDomain Indicates whether the art piece is in the public domain.
 * @property galleryNumber The gallery number where the art piece is displayed.
 */
data class Artpiece(
    val objectID: Int,
    var primaryImageSmall: String,
    var title: String,
    var department: String,
    var artistDisplayName: String,
    var artistNationality: String,
    var artistBeginDate: String,
    var artistEndDate: String,
    var objectDate: String,
    var medium: String,
    var dimensions: String,
    var country: String,
    var culture: String,
    var period: String,
    var dynasty: String,
    var publicDomain: Boolean,
    var galleryNumber: String,
)