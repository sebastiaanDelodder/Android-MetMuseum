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

class ArtOverviewViewModel(private val artpiecesRepository: ArtpiecesRepository) : ViewModel(){
    private val _uiState = MutableStateFlow(ArtOverviewState())
    val uiState: StateFlow<ArtOverviewState> = _uiState.asStateFlow()

    /*
    * Note: uiListState is a hot flow (.stateIn makes it so) --> it updates given a scope (viewmodelscope)
    * when no updates are required (lifecycle) the subscription is stopped after a timeout
    * */
    lateinit var uiListState : StateFlow<ArtpieceListState>

    // keeping the state of the api request
    var artpieceApiState: ArtpieceApiState by mutableStateOf(ArtpieceApiState.Loading)
        private set


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
                                Log.i("GET ID", "index $i is out of bounds")

                            }
                        }
                    }
                }

                _uiState.update {
                        currentState ->
                            currentState.copy(
                                currentLoadedIds = currentState.currentLoadedIds + numberOfArtpieces
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
            //show a toast? save a log on firebase? ...
            //set the error state
            //TODO
            artpieceApiState = ArtpieceApiState.Error
        } catch (e: Error){
            //show a toast? save a log on firebase? ...
            //set the error state
            //TODO
            artpieceApiState = ArtpieceApiState.Error
        }
    }

    fun changeSearch(search: String){
        _uiState.update {
            currentState ->
            currentState.copy(
                search = search
            )
        }
    }

    fun changeDepartment(department: Department){
        Log.i("Change dep", "CHANGING DEPARTMENT")

        if (uiState.value.department == null){
            Log.i("Change dep", "IS NULL")
            _uiState.update {
                    currentState ->
                currentState.copy(
                    department = department,
                    currentLoadedIds = 0,
                    currentScrollTo = 0
                )
            }

            getRepoArtpieces(40)
        } else if (uiState.value.department != null && uiState.value.department!!.departmentId != department.departmentId){
            Log.i("Change dep", "IS NEW DEPARTMENT")
            _uiState.update {
                    currentState ->
                currentState.copy(
                    department = department,
                    currentLoadedIds = 0,
                    currentScrollTo = 0
                )
            }
            getRepoArtpieces(40)
        } else if (uiState.value.department != null && uiState.value.department!!.departmentId == department.departmentId){
            Log.i("Change dep", "SAME DEPARTMENT")
            _uiState.update {
                    currentState ->
                currentState.copy(
                    //currentScrollTo = 0
                )
            }
            Log.i("Change dep", "${uiState.value.currentScrollTo}")
        }
    }

    fun setArtpiece(artpiece: Artpiece?) {
        _uiState.update {
            currentState ->
            currentState.copy(
                selectedArtpiece = artpiece
            )
        }
    }

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
                                Log.i("GET ID", "index $i is out of bounds")

                            }
                        }
                    }
                }


            }

            //artpieceApiState = ArtpieceApiState.Success
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

            Log.i("TESSSSTTT", "${uiState.value.currentScrollTo}")
            artpieceApiState = ArtpieceApiState.Success
        }
        catch (e: IOException){
            //show a toast? save a log on firebase? ...
            //set the error state
            //TODO
            artpieceApiState = ArtpieceApiState.Error
        } catch (e: Error){
            //show a toast? save a log on firebase? ...
            //set the error state
            //TODO
            artpieceApiState = ArtpieceApiState.Error
        }
    }

    fun setLastLoaded(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentLoadedIds = index
            ) }
    }


    //object to tell the android framework how to handle the parameter of the viewmodel
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