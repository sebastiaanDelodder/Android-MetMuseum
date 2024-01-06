package com.example.metmuseum.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.metmuseum.R
import com.example.metmuseum.data.ArtpieceSampler
import com.example.metmuseum.model.Artpiece
import kotlinx.coroutines.launch


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ArtCardPrev() {
    ArtScreenColumn(
        artpieces = ArtpieceSampler.getAll(),
        onArtpieceClick = {},
        lazyListState = LazyListState(),
        currentIndex = 0,
        loadMore = {},
    )
}

@Composable
fun ArtScreenColumn(
    artpieces: List<Artpiece>,
    onArtpieceClick: (artpiece: Artpiece) -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    currentIndex: Int,
    loadMore: () -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_large)),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier,
        state = lazyListState
    ) {
        items(artpieces) { item ->
            ArtCard(
                modifier = modifier,
                art = item,
                onArtpieceClick = onArtpieceClick
            )
            //if last item on screen, load more
            if (item == artpieces.last()) {
                loadMore()
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(currentIndex) {
        if (currentIndex != 0) {
            Log.i("ArtScreenColumn", "scrolling to $currentIndex")
            coroutineScope.launch {
                lazyListState.scrollToItem(currentIndex)
            }
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
            if (art.primaryImageSmall == "") {
                Image(
                    painter = painterResource(R.drawable.placeholder),
                    contentDescription = stringResource(R.string.image_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.img_size_medium))
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(art.primaryImageSmall)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = stringResource(R.string.image_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.img_size_medium))
                )
            }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_medium)))
            Text(
                text = art.title,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 3
            )
        }
    }
}