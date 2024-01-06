package com.example.metmuseum.model

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