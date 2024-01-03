package com.example.metmuseum.model

data class Artpiece (
    val objectID: Int,
    var isPublicDomain: Boolean,
    var primaryImage: String,
    var primaryImageSmall: String,
    var title: String,
    var department: String,
)