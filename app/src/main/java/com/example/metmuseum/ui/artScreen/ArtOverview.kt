package com.example.metmuseum.ui.artScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metmuseum.R
import com.example.metmuseum.model.Department
import com.example.metmuseum.ui.artScreen.viewModels.ArtOverviewModel
import com.example.metmuseum.ui.components.ArtScreenColumn
import com.example.metmuseum.ui.components.Searchbar

@Composable
fun ArtOverview(
    department: Department,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    artOverviewModel: ArtOverviewModel = viewModel()
) {
    val artOverviewState by artOverviewModel.uiState.collectAsState()

    Column {
        IconButton(
            onClick = { onBack() },
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
            )
        }
        Text(text = "Department: " + department.displayName)
        Searchbar(
            search = artOverviewState.search,
            onValueChange = { artOverviewModel.changeSearch(it) },
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_large))
        )
        Text(text = "Search: " + artOverviewState.search)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_large)))
        ArtScreenColumn(
            artpieces = artOverviewState.currentArtPieces,
            modifier = modifier)
    }
}