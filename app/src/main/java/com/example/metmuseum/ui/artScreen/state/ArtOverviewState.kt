package com.example.metmuseum.ui.artScreen.state

import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department

data class ArtOverviewState(
    val department: Department? = null,
    val search: String = "",
    var currentObjectIdList: List<Int> = listOf(),
    var currentLoadedIds: Int = 0,
    var selectedArtpiece : Artpiece? = null,
)

data class ArtpieceListState(
    val artpieces: List<Artpiece> = listOf()
)

sealed interface ArtpieceApiState {
    object Success : ArtpieceApiState
    object Error: ArtpieceApiState
    object Loading : ArtpieceApiState
}