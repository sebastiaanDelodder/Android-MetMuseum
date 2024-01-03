package com.example.metmuseum.ui.artScreen.state

import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department

data class ArtOverviewState(
    //val currentArtPieces: List<Artpiece>,
    val search: String = "",
    var currentList: List<Int> = listOf()
)

data class ArtpieceListState(
    val artpieces: List<Artpiece> = listOf()
)

sealed interface ArtpieceApiState {
    object Success : ArtpieceApiState
    object Error: ArtpieceApiState
    object Loading : ArtpieceApiState
}