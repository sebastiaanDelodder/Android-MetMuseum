package com.example.metmuseum.data

import android.util.Log
import com.example.metmuseum.model.Artpiece

object ArtpieceSampler {
    val sampleArtpieces = listOf<Artpiece>(
        Artpiece(
            objectID = 1,
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
            title = "Spoon Dish",
            department = "Arts of Africa, Oceania, and the Americas",
            artistDisplayName = "Artist",
            artistNationality = "Australian",
            artistBeginDate = "1990",
            artistEndDate = "2000",
            objectDate = "1995",
            medium = "Wood",
            dimensions = "10 x 10 x 10 cm",
            country = "Australia",
            culture = "Australian",
            period = "1990s",
            dynasty = "1990s",
            publicDomain = true,
            galleryNumber = "1"
        ),
        Artpiece(
            objectID = 2,
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
            title = "Spoon Dish",
            department = "Arts of Africa, Oceania, and the Americas",
            artistDisplayName = "Artist",
            artistNationality = "Australian",
            artistBeginDate = "1990",
            artistEndDate = "2000",
            objectDate = "1995",
            medium = "Wood",
            dimensions = "10 x 10 x 10 cm",
            country = "Australia",
            culture = "Australian",
            period = "1990s",
            dynasty = "1990s",
            publicDomain = true,
            galleryNumber = "1"
        ),
        Artpiece(
            objectID = 3,
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
            title = "Knife",
            department = "Arts of Africa, Oceania, and the Americas",
            artistDisplayName = "Artist",
            artistNationality = "Australian",
            artistBeginDate = "1990",
            artistEndDate = "2000",
            objectDate = "1995",
            medium = "Wood",
            dimensions = "10 x 10 x 10 cm",
            country = "Australia",
            culture = "Australian",
            period = "1990s",
            dynasty = "1990s",
            publicDomain = true,
            galleryNumber = "1"
        ),
        Artpiece(
            objectID = 4,
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
            title = "Spoon Dish",
            department = "Arts of Africa, Oceania, and the Americas",
            artistDisplayName = "Artist",
            artistNationality = "Australian",
            artistBeginDate = "1990",
            artistEndDate = "2000",
            objectDate = "1995",
            medium = "Wood",
            dimensions = "10 x 10 x 10 cm",
            country = "Australia",
            culture = "Australian",
            period = "1990s",
            dynasty = "1990s",
            publicDomain = true,
            galleryNumber = "1"
        ),
        Artpiece(
            objectID = 5,
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
            title = "Spoon Dish",
            department = "Arts of Africa, Oceania, and the Americas",
            artistDisplayName = "Artist",
            artistNationality = "Australian",
            artistBeginDate = "1990",
            artistEndDate = "2000",
            objectDate = "1995",
            medium = "Wood",
            dimensions = "10 x 10 x 10 cm",
            country = "Australia",
            culture = "Australian",
            period = "1990s",
            dynasty = "1990s",
            publicDomain = true,
            galleryNumber = "1"
        ),
    )

    val getAll: () -> List<Artpiece> = {
        Log.i("ArtPieceSampler", "getAll called")
        sampleArtpieces
    }
}