package com.example.metmuseum.ui.artScreen.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.metmuseum.Application
import com.example.metmuseum.data.repository.ArtpiecesRepository
import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department
import com.example.metmuseum.ui.artScreen.state.ArtOverviewState
import com.example.metmuseum.ui.artScreen.state.ArtpieceApiState
import com.example.metmuseum.ui.artScreen.state.ArtpieceListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel class for managing the UI state and API requests related to art pieces overview.
 *
 * @property artpiecesRepository Repository for handling art piece data operations.
 * @property _uiState MutableStateFlow representing the internal UI state.
 * @property uiState Public StateFlow representing the UI state that external components can observe.
 * @property uiListState StateFlow representing the state of the art pieces list UI.
 * @property artpieceApiState State representing the current state of the art piece API request.
 *
 * @constructor Creates an instance of [ArtOverviewViewModel] with the provided [ArtpiecesRepository].
 *
 * @see ViewModel
 */
class ArtOverviewViewModel(private val artpiecesRepository: ArtpiecesRepository) : ViewModel(){
    private val _uiState = MutableStateFlow(ArtOverviewState())
    val uiState: StateFlow<ArtOverviewState> = _uiState.asStateFlow()

    lateinit var uiListState : StateFlow<ArtpieceListState>

    var artpieceApiState: ArtpieceApiState by mutableStateOf(ArtpieceApiState.Loading)
        private set


    /**
     * Fetches art pieces from the repository and updates the UI state accordingly.
     *
     * @param numberOfArtpieces The number of art pieces to fetch.
     */
    private fun getRepoArtpieces(numberOfArtpieces: Int){
        try {
            viewModelScope.launch {
                artpiecesRepository.refresh(uiState.value.department!!.departmentId).collect {
                    uiState.value.currentObjectIdList = it.sorted()
                    Log.i("ArtOverviewViewModel", "Total collected ID's: ${uiState.value.currentObjectIdList.size}")
                }

                //todo max size check
                if (uiState.value.currentObjectIdList.size < uiState.value.currentLoadedIds + numberOfArtpieces){
                    artpieceApiState = ArtpieceApiState.Error
                    return@launch
                } else {
                    for (i in uiState.value.currentLoadedIds .. uiState.value.currentLoadedIds + numberOfArtpieces) {
                        viewModelScope.launch {
                            if (uiState.value.currentObjectIdList.size > i){
                                artpiecesRepository.refreshArtPiece(uiState.value.currentObjectIdList[i])
                            } else {
                                Log.e("GET ID", "index $i is out of bounds")
                            }
                        }
                    }
                }

                _uiState.update {
                        currentState ->
                            currentState.copy(
                                currentLoadedIds = currentState.currentLoadedIds + numberOfArtpieces,
                                currentScrollTo = _uiState.value.currentScrollTo
                            )
                }
            }

            uiListState = artpiecesRepository.getArtpieces(uiState.value.department!!).map {
                ArtpieceListState(it)
            }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = ArtpieceListState()
                )

            artpieceApiState = ArtpieceApiState.Success
        }
        catch (e: IOException){
            Log.e(("ArtOverviewViewModel"), e.stackTraceToString())
            artpieceApiState = ArtpieceApiState.Error
        } catch (e: Error){
            Log.e(("ArtOverviewViewModel"), e.stackTraceToString())
            artpieceApiState = ArtpieceApiState.Error
        }
    }

    /**
     * Changes the current department in the UI state and triggers a reload of artpieces for the new department.
     *
     * @param department The new [Department] to set in the UI state.
     */
    fun changeDepartment(department: Department){
        Log.i("ArtOverviewViewModel", "CHANGING DEPARTMENT")

        if (uiState.value.department != null && uiState.value.department!!.departmentId == department.departmentId){
            Log.i("ArtOverviewViewModel", "SAME DEPARTMENT")
        } else {
            Log.i("ArtOverviewViewModel", "IS NEW DEPARTMENT")
            _uiState.update {
                    currentState ->
                currentState.copy(
                    department = department,
                    currentLoadedIds = 0,
                    currentScrollTo = 0
                )
            }
            getRepoArtpieces(40)
        }
    }

    /**
     * Sets the selected artpiece in the UI state.
     *
     * @param artpiece The [Artpiece] to set as the selected artpiece.
     */
    fun setArtpiece(artpiece: Artpiece?) {
        _uiState.update {
            currentState ->
            currentState.copy(
                selectedArtpiece = artpiece
            )
        }
    }

    /**
     * When at the bottom of the page this function is called to load more artpieces.
     *
     * @param firstVisibleItemIndex The index of the first visible item in the UI (a.k.a. the index of the top item).
     */
    fun loadMore(firstVisibleItemIndex: Int) {
        artpieceApiState = ArtpieceApiState.Loading
        try {
            viewModelScope.launch {
                //todo max size check
                if (uiState.value.currentObjectIdList.size < uiState.value.currentLoadedIds + 20){
                    //artpieceApiState = ArtpieceApiState.Error
                    return@launch
                } else {
                    for (i in uiState.value.currentLoadedIds .. uiState.value.currentLoadedIds + 20) {
                        viewModelScope.launch {
                            if (uiState.value.currentObjectIdList.size > i){
                                artpiecesRepository.refreshArtPiece(uiState.value.currentObjectIdList[i])
                            } else {
                                Log.e("GET ID", "index $i is out of bounds")
                            }
                        }
                    }
                }
            }


            uiListState = artpiecesRepository.getArtpieces(uiState.value.department!!).map {
                _uiState.update {
                        currentState ->
                    currentState.copy(
                        currentLoadedIds = currentState.currentLoadedIds + 20,
                        currentScrollTo = firstVisibleItemIndex
                    )
                }
                ArtpieceListState(it)
            }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = ArtpieceListState()
                )

            artpieceApiState = ArtpieceApiState.Success
        }
        catch (e: IOException){
            Log.e("ArtOverviewViewModel", e.stackTraceToString())
            artpieceApiState = ArtpieceApiState.Error
        } catch (e: Error){
            Log.e("ArtOverviewViewModel", e.stackTraceToString())
            artpieceApiState = ArtpieceApiState.Error
        }
    }


    /**
     * A companion object for providing a [ViewModelProvider.Factory] for creating instances of [ArtOverviewViewModel].
     *
     * The [Factory] defines how the Android framework should handle the parameters of the [ArtOverviewViewModel].
     * It retrieves the necessary dependencies, such as the [ArtpiecesRepository], from the application container.
     *
     * @see ViewModelProvider.Factory
     * @see ArtOverviewViewModel
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {

            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application)
                val artpiecesRepository = application.container.artpiecesRepository
                ArtOverviewViewModel(
                    artpiecesRepository = artpiecesRepository
                )
            }
        }
    }
}