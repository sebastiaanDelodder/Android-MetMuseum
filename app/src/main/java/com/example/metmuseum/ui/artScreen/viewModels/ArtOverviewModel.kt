package com.example.metmuseum.ui.artScreen.viewModels

import androidx.lifecycle.ViewModel
import com.example.metmuseum.data.ArtpieceSampler
import com.example.metmuseum.ui.artScreen.state.ArtOverviewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArtOverviewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ArtOverviewState(ArtpieceSampler.getAll()))
    val uiState: StateFlow<ArtOverviewState> = _uiState.asStateFlow()

    fun changeSearch(search: String){
        _uiState.update {
            currentState ->
            currentState.copy(
                search = search
            )
        }
    }
}