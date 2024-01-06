package com.example.metmuseum.ui.artScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metmuseum.R
import com.example.metmuseum.model.Department
import com.example.metmuseum.ui.artScreen.state.ArtpieceApiState
import com.example.metmuseum.ui.artScreen.viewModels.ArtOverviewViewModel
import com.example.metmuseum.ui.components.ArtDetail
import com.example.metmuseum.ui.components.ArtScreenColumn
import com.example.metmuseum.ui.components.ErrorScreen
import com.example.metmuseum.ui.components.LoadingScreen
import com.example.metmuseum.ui.theme.MetMuseumTheme

@Composable
fun ArtOverview(
    department: Department,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    artOverviewViewModel: ArtOverviewViewModel = viewModel(factory = ArtOverviewViewModel.Factory)
) {
    Log.i("vm inspection", "ArtOverview composition")
    val artOverviewState by artOverviewViewModel.uiState.collectAsState()

    Log.i("ArtOverview", "Initializing department to $department")
    artOverviewViewModel.changeDepartment(department)

    //use the ApiState
    val artpieceApiState = artOverviewViewModel.artpieceApiState

    val artpieceListState by artOverviewViewModel.uiListState.collectAsState()

    val lazyListState = rememberLazyListState()

    //TODO: WHEN OFFLINE NEW DEP -> EMPTY LIST: NO INTERNET

    Column {
        when(artpieceApiState) {
            is ArtpieceApiState.Loading -> {
                LoadingScreen()
            }
            is ArtpieceApiState.Error -> {
                IconButton(
                    onClick = { onBack() },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                    )
                }
                ErrorScreen()
            }
            is ArtpieceApiState.Success -> {
                if (artOverviewState.selectedArtpiece == null) {
                    Log.i("ArtOverview", "selectedArtpiece is null")
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .background(color = MaterialTheme.colorScheme.tertiary)
                            .fillMaxWidth()
                            .padding(
                                dimensionResource(id = R.dimen.padding_medium),
                            )
                    ){
                        IconButton(
                            onClick = onBack,
                            modifier = modifier
                                .width(dimensionResource(id = R.dimen.width_button))
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back),
                            )
                        }
                        Text(
                            text = department.displayName,
                            textAlign = TextAlign.End,

                            fontSize = 20.sp
                        )
                    }
                    ArtScreenColumn(
                        artpieces = artpieceListState.artpieces,
                        onArtpieceClick = { artOverviewViewModel.setArtpiece(it) },
                        modifier = modifier,
                        lazyListState = lazyListState,
                        currentIndex = artOverviewState.currentScrollTo,
                        loadMore = { artOverviewViewModel.loadMore(lazyListState.firstVisibleItemIndex) },

                    )
                } else {
                    Log.i("ArtOverview", "selectedArtpiece is ${artOverviewState.selectedArtpiece}")
                    ArtDetail(
                        artpiece = artOverviewState.selectedArtpiece!!,
                        onBack = { artOverviewViewModel.setArtpiece(null) }
                    )
                }
            }
        }
    }
}