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
import com.example.metmuseum.ui.artScreen.state.ArtOverviewState
import com.example.metmuseum.ui.artScreen.state.ArtpieceApiState
import com.example.metmuseum.ui.artScreen.state.ArtpieceListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
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

    init {
        // initialize the uiListState
        getRepoArtpieces()
        Log.i("vm inspection", "ArtpieceViewModel init")
    }

    private fun getRepoArtpieces(){
        try {
            viewModelScope.launch {
                artpiecesRepository.refresh(1).collect{
                    Log.i("vm inspection", "collecting")
                    uiState.value.currentList = it
                    Log.i("vm inspection", "${uiState.value.currentList.size}")
                }
            }

            uiListState = artpiecesRepository.getArtpieces().map { ArtpieceListState(it) }
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
            Log.e("vm inspection", "$e")
            artpieceApiState = ArtpieceApiState.Error
        } catch (e: Error){
            //show a toast? save a log on firebase? ...
            //set the error state
            //TODO
            Log.e("vm inspection", "$e")
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