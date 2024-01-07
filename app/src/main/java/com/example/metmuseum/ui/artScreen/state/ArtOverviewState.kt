package com.example.metmuseum.ui.artScreen.state

import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department

/**
 * Data class representing the state for the art overview screen.
 *
 * @property department The selected [Department]. Defaults to null.
 * @property currentObjectIdList The list of current object IDs. Defaults to an empty list.
 * @property currentLoadedIds The number of currently loaded object IDs. Defaults to 0.
 * @property selectedArtpiece The selected Artpiece. Defaults to null.
 * @property currentScrollTo The position to scroll to. Defaults to 0.
 */
data class ArtOverviewState(
    val department: Department? = null,
    var currentObjectIdList: List<Int> = listOf(),
    var currentLoadedIds: Int = 0,
    var selectedArtpiece : Artpiece? = null,
    var currentScrollTo: Int =0,
)

/**
 * Data class representing the state for the list of Artpieces.
 *
 * @property artpieces The list of Artpieces. Defaults to an empty list.
 */
data class ArtpieceListState(
    val artpieces: List<Artpiece> = listOf()
)

/**
 * Sealed interface representing the state of an Artpiece API operation.
 */
sealed interface ArtpieceApiState {
    /**
     * Represents a successful Artpiece API operation.
     */
    object Success : ArtpieceApiState

    /**
     * Represents an error during an Artpiece API operation.
     */
    object Error: ArtpieceApiState

    /**
     * Represents the loading state during an Artpiece API operation.
     */
    object Loading : ArtpieceApiState
}