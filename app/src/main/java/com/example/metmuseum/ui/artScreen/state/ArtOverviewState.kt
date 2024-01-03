package com.example.metmuseum.ui.artScreen.state

import com.example.metmuseum.model.Artpiece

data class ArtOverviewState(
    //val currentArtPieces: List<Artpiece>,
    val search: String = "",
    var currentObjectIdList: List<Int> = listOf(),
    var currentLoadedIds: Int = 1,
)

data class ArtpieceListState(
    val artpieces: List<Artpiece> = listOf()
)

sealed interface ArtpieceApiState {
    object Success : ArtpieceApiState
    object Error: ArtpieceApiState
    object Loading : ArtpieceApiState
}