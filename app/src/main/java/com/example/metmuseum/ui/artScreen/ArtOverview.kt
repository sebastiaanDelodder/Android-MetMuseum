package com.example.metmuseum.ui.artScreen

import android.util.Log
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
import com.example.metmuseum.ui.artScreen.state.ArtpieceApiState
import com.example.metmuseum.ui.artScreen.viewModels.ArtOverviewViewModel
import com.example.metmuseum.ui.components.ArtScreenColumn
import com.example.metmuseum.ui.components.ErrorScreen
import com.example.metmuseum.ui.components.LoadingScreen
import com.example.metmuseum.ui.components.Searchbar

@Composable
fun ArtOverview(
    department: Department,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    artOverviewViewModel: ArtOverviewViewModel = viewModel(factory = ArtOverviewViewModel.Factory)
) {
    Log.i("vm inspection", "ArtOverview composition")
    val artOverviewState by artOverviewViewModel.uiState.collectAsState()

    if(artOverviewState.department == null){
        Log.i("vm inspection", "CHANGEEEE")
        artOverviewViewModel.changeDepartment(department)
    }

    val artpieceListState by artOverviewViewModel.uiListState.collectAsState()

    //use the ApiState
    val artpieceApiState = artOverviewViewModel.artpieceApiState

    Column {
        when(artpieceApiState) {
            is ArtpieceApiState.Loading -> {
                LoadingScreen()
            }
            is ArtpieceApiState.Error -> {
                ErrorScreen()
            }
            is ArtpieceApiState.Success -> {
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
                    onValueChange = { artOverviewViewModel.changeSearch(it) },
                    modifier = modifier
                        .padding(dimensionResource(id = R.dimen.padding_large))
                )
                Text(text = "Search: " + artOverviewState.search)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_large)))
                ArtScreenColumn(
                    artpieces = artpieceListState.artpieces,
                    modifier = modifier)
            }
        }
    }
}