package com.example.metmuseum.data

import android.util.Log
import com.example.metmuseum.model.Artpiece

object ArtpieceSampler {
    val sampleArtpieces = listOf<Artpiece>(
        Artpiece(
            objectID = 1,
            isPublicDomain = true,
            primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/LC-45_24_17-006.jpg",
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/aa/web-large/LC-45_24_17-006.jpg",
            title = "Knife Handle (Kozuka)",
            department = "Arts of Africa, Oceania, and the Americas"
        ),
        Artpiece(
            objectID = 2,
            isPublicDomain = true,
            primaryImage = "https://images.metmuseum.org/CRDImages/ad/original/136397.jpg",
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
            title = "Spoon Dish",
            department = "Arts of Africa, Oceania, and the Americas"
        ),
        Artpiece(
            objectID = 3,
            isPublicDomain = true,
            primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/LC-45_24_17-006.jpg",
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/aa/web-large/LC-45_24_17-006.jpg",
            title = "Knife Handle (Kozuka)",
            department = "Arts of Africa, Oceania, and the Americas"
        ),
        Artpiece(
            objectID = 4,
            isPublicDomain = true,
            primaryImage = "https://images.metmuseum.org/CRDImages/ad/original/136397.jpg",
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
            title = "Spoon Dish",
            department = "Arts of Africa, Oceania, and the Americas"
        ),
        Artpiece(
            objectID = 5,
            isPublicDomain = true,
            primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/LC-45_24_17-006.jpg",
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/aa/web-large/LC-45_24_17-006.jpg",
            title = "Knife Handle (Kozuka)",
            department = "Arts of Africa, Oceania, and the Americas"
        ),
        Artpiece(
            objectID = 6,
            isPublicDomain = true,
            primaryImage = "https://images.metmuseum.org/CRDImages/ad/original/136397.jpg",
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
            title = "Spoon Dish",
            department = "Arts of Africa, Oceania, and the Americas"
        ),
        Artpiece(
            objectID = 7,
            isPublicDomain = true,
            primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/LC-45_24_17-006.jpg",
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/aa/web-large/LC-45_24_17-006.jpg",
            title = "Knife Handle (Kozuka)",
            department = "Arts of Africa, Oceania, and the Americas"
        ),
    )

    val getAll: () -> List<Artpiece> = {
        Log.i("ArtPieceSampler", "getAll called")
        sampleArtpieces
    }
}