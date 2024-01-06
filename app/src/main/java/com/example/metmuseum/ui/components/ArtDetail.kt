package com.example.metmuseum.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.metmuseum.R
import com.example.metmuseum.model.Artpiece

@Composable
fun ArtDetail(
    artpiece: Artpiece,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
    ){
        IconButton(
            onClick = { onBack() },
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
            )
        }
        Text(text = artpiece.title)
        Text(text = artpiece.isPublicDomain.toString())
        Text(text = artpiece.primaryImage)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artpiece.primaryImageSmall)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = stringResource(R.string.image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
    }
}