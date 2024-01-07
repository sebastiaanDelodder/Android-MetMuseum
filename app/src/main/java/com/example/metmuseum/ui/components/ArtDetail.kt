package com.example.metmuseum.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.metmuseum.R
import com.example.metmuseum.model.Artpiece

/**
 * Composable function for displaying a preview of the ArtDetail.
 *
 * @see ArtDetail
 */
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun Artdetailprev(
) {
    ArtDetail(
        artpiece = Artpiece(
            objectID = 1,
            primaryImageSmall = "",
            department = "American Decorative Arts",
            title = "Painting",
            culture = "Culture",
            period = "Period",
            dynasty = "Dynasty",
            artistDisplayName = "Name",
            artistNationality = "Nat",
            artistBeginDate = "Born",
            artistEndDate = "Died",
            objectDate = "DATE",
            medium = "Medium",
            dimensions = "dimjqs caj caj cja kj avkj zev ezkv ekqv kjqe vjezkq vzjk",
            country = "United States",
            galleryNumber = "6",
            publicDomain = true,
            ), onBack = { })
}

/**
 * Composable function for displaying detailed information about an Artpiece.
 *
 * @param artpiece The [Artpiece] to display details for.
 * @param onBack Callback to be invoked when the back button is clicked.
 * @param modifier Optional [Modifier] to apply to the composable.
 */
@Composable
fun ArtDetail(
    artpiece: Artpiece,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row (
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ){
                IconButton(
                    onClick = { onBack() },
                    modifier = modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                    )
                }
            }
            if (artpiece.primaryImageSmall == "") {
                Image(
                    painter = painterResource(R.drawable.placeholder),
                    contentDescription = stringResource(R.string.image_description),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.height(dimensionResource(id = R.dimen.img_size_xlarge))
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(artpiece.primaryImageSmall)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = stringResource(R.string.image_description),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.height(dimensionResource(id = R.dimen.img_size_xlarge))
                )
            }

            Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_xxlarge)))
            Text(
                text = artpiece.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_large),
                    )
            )
            Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_xxlarge)))

            Row(
                modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_large),
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.department),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = artpiece.department,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }

            Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

            ArtistComponent(artpiece = artpiece)

            Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

            ArtInfo(artpiece = artpiece)

            Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

            GlobalInfo(artpiece = artpiece)

            Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_large),
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = stringResource(id = R.string.publicDomain),
                    style = MaterialTheme.typography.titleMedium
                )
                if(artpiece.publicDomain){
                    Text(
                        text = stringResource(id = R.string.yes),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.no),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

/**
 * Composable function for displaying information about the artist of an artpiece.
 *
 * @param artpiece The [Artpiece] containing artist information.
 * @param modifier Modifier for styling and layout customization.
 */
@Composable
fun ArtistComponent(
    artpiece: Artpiece,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_large),
            )
    ) {
        Text(
            text = stringResource(id = R.string.artist),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.fillMaxWidth()
        )

        if (artpiece.artistDisplayName != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.artistName),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.artistDisplayName,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if (artpiece.artistNationality != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.artistNationality),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.artistNationality,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if (artpiece.artistBeginDate != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.artistBeginDate),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.artistBeginDate,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if (artpiece.artistEndDate != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.artistEndDate),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.artistEndDate,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }
    }
}

/**
 * Composable function for displaying detailed information about an Artpiece.
 *
 * @param artpiece The [Artpiece] object containing information to be displayed.
 * @param modifier Modifier for customizing the layout and appearance of the composable.
 */
@Composable
fun ArtInfo(
    artpiece: Artpiece,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_large),
            )
    ){
        Text(
            text = stringResource(id = R.string.otherInfo),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.fillMaxWidth()
        )

        if (artpiece.objectDate != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.objectDate),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.objectDate,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if (artpiece.medium != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.medium),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.medium,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if (artpiece.dimensions != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.dimensions),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.dimensions,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }
    }
}

/**
 * Composable function for displaying global information about an Artpiece.
 *
 * @param artpiece The [Artpiece] object containing information to be displayed.
 * @param modifier Optional modifier for customizing the appearance of the composable.
 */
@Composable
fun GlobalInfo(
    artpiece: Artpiece,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_large),
            )
    ){
        Text(
            text = stringResource(id = R.string.globalInfo),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.fillMaxWidth()
        )

        if(artpiece.country != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.country),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.country,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if(artpiece.culture != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.culture),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.culture,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if(artpiece.period != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.period),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.period,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if(artpiece.dynasty != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.dynasty),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.dynasty,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }

        if(artpiece.galleryNumber != ""){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.galleryNumber),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = artpiece.galleryNumber,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .widthIn(
                            max = dimensionResource(id = R.dimen.width_text),
                        )
                )
            }
        }
    }
}