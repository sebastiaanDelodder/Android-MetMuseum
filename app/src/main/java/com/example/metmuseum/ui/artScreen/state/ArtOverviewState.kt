package com.example.metmuseum.ui.artScreen.state

import com.example.metmuseum.model.Artpiece

data class ArtOverviewState(
    val currentArtPieces: List<Artpiece>,
    val search: String = ""
)

data class ArtpieceListState(
    val artpieces: List<Artpiece> = listOf()
)