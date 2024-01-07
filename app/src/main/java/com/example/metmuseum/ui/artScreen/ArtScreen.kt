package com.example.metmuseum.ui.artScreen

import android.util.Log
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metmuseum.ui.artScreen.viewModels.ArtScreenViewModel


/**
 * A preview composable function for the ArtScreen. It displays the ArtScreen in the Android Studio Layout Editor.
 *
 * @see Preview
 * @see ArtScreen
 */
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 650)
@Composable
fun ArtScreenPrev() {
    ArtScreen()
}

/**
 * Composable function for displaying the ArtScreen UI.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param artScreenViewModel The ViewModel for managing the state of the ArtScreen.
 *
 * @see Composable
 * @see Surface
 * @see Color
 * @see ArtScreenViewModel
 * @see ArtOverview
 * @see DepartmentScreen
 */
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