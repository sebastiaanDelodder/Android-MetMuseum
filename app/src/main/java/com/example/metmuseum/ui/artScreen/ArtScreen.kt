package com.example.metmuseum.ui.artScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metmuseum.model.Department
import com.example.metmuseum.ui.artScreen.viewModels.ArtScreenViewModel

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 650)
@Composable
fun ArtScreenPrev() {
    ArtScreen()
}

@Composable
fun ArtScreen(
    modifier : Modifier = Modifier,
    artScreenViewModel: ArtScreenViewModel = viewModel()
) {
    Log.i("vm inspection", "ArtScreen composition")
    val artScreenState by artScreenViewModel.uiState.collectAsState()

    Surface(
        modifier = modifier,
        color = Color.Transparent,
    ) {
        if (artScreenState.currentDepartment == null){
            DepartmentScreen(onDepartmentClick = { artScreenViewModel.setDepartment(it) })
        } else {
            ArtOverview(
                department = artScreenState.currentDepartment!!,
                onBack = { artScreenViewModel.setDepartment(null) },
            )
        }
    }
}