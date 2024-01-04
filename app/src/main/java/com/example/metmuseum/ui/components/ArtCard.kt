package com.example.metmuseum.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metmuseum.R
import com.example.metmuseum.data.ArtpieceSampler
import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ArtCardPrev() {
    ArtScreenColumn(artpieces = ArtpieceSampler.getAll(), onArtpieceClick = {})
}

@Composable
fun ArtScreenColumn(
    artpieces: List<Artpiece>,
    onArtpieceClick: (artpiece: Artpiece) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_large)),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier

    ) {
        items(artpieces) { item ->
            ArtCard(
                modifier = modifier,
                art = item,
                onArtpieceClick = onArtpieceClick
            )
        }
    }
}

@Composable
fun ArtCard(
    art : Artpiece,
    onArtpieceClick: (artpiece: Artpiece) -> Unit,
    modifier : Modifier = Modifier
) {
    Surface (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            //.padding(4.dp)
            .fillMaxWidth()
            .clickable { onArtpieceClick(art) },
        color = MaterialTheme.colorScheme.secondaryContainer,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.testimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(dimensionResource(id = R.dimen.img_size_medium))
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_medium)))
            Text(
                text = art.title,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}