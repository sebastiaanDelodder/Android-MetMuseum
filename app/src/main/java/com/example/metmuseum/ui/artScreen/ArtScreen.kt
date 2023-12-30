package com.example.metmuseum.ui.artScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.ui.components.ArtCard

var listOfArt = listOf(
    Artpiece(
        objectID = 1,
        isPublicDomain = true,
        primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/LC-45_24_17-006.jpg",
        primaryImageSmall = "https://images.metmuseum.org/CRDImages/aa/web-large/LC-45_24_17-006.jpg",
        title = "Knife Handle (Kozuka)"
    ),
    Artpiece(
        objectID = 2,
        isPublicDomain = true,
        primaryImage = "https://images.metmuseum.org/CRDImages/ad/original/136397.jpg",
        primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
        title = "Spoon Dish"
    ),
    Artpiece(
        objectID = 3,
        isPublicDomain = true,
        primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/LC-45_24_17-006.jpg",
        primaryImageSmall = "https://images.metmuseum.org/CRDImages/aa/web-large/LC-45_24_17-006.jpg",
        title = "Knife Handle (Kozuka)"
    ),
    Artpiece(
        objectID = 4,
        isPublicDomain = true,
        primaryImage = "https://images.metmuseum.org/CRDImages/ad/original/136397.jpg",
        primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
        title = "Spoon Dish"
    ),

)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 300)
@Composable
fun ArtScreenPrev() {
    ArtScreenColumn()
}

@Composable
fun ArtScreenColumn(
    modifier : Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier

    ) {
        items(listOfArt) { item ->
            ArtCard(
                modifier = modifier,
                art = item
            )
        }
    }
}