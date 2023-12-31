package com.example.metmuseum.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metmuseum.R
import com.example.metmuseum.model.Artpiece

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
    Artpiece(
        objectID = 5,
        isPublicDomain = true,
        primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/LC-45_24_17-006.jpg",
        primaryImageSmall = "https://images.metmuseum.org/CRDImages/aa/web-large/LC-45_24_17-006.jpg",
        title = "Knife Handle (Kozuka)"
    ),
    Artpiece(
        objectID = 6,
        isPublicDomain = true,
        primaryImage = "https://images.metmuseum.org/CRDImages/ad/original/136397.jpg",
        primaryImageSmall = "https://images.metmuseum.org/CRDImages/ad/web-large/136397.jpg",
        title = "Spoon Dish"
    ),
    Artpiece(
        objectID = 7,
        isPublicDomain = true,
        primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/LC-45_24_17-006.jpg",
        primaryImageSmall = "https://images.metmuseum.org/CRDImages/aa/web-large/LC-45_24_17-006.jpg",
        title = "Knife Handle (Kozuka)"
    ),
)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ArtCardPrev() {
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

@Composable
fun ArtCard(
    art : Artpiece,
    modifier : Modifier = Modifier
) {
    Surface (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            //.padding(4.dp)
            .fillMaxWidth(),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.width(255.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.testimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)

            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = art.title)
        }
    }
}