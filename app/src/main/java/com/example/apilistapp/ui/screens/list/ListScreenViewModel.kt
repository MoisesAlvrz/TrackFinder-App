package com.example.apilistapp.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.domain.Album
import com.example.apilistapp.domain.Track
import com.example.apilistapp.data.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ListScreenUiState {
    object Loading: ListScreenUiState

    data class Success(
        val fetchedAlbums: List<Album> = emptyList(),
        val fetchedTracks: List<Track> = emptyList()
    ): ListScreenUiState

    data class Error(
        val message: String
    ): ListScreenUiState
}

class ListScreenViewModel: ViewModel() {
    private val repository = ApiRepository()

    private val _uiState = MutableStateFlow<ListScreenUiState>(ListScreenUiState.Loading)
    val uiState: StateFlow<ListScreenUiState> = _uiState.asStateFlow()

    private val _enteredQuery = MutableStateFlow<String>("")
    val enteredQuery: StateFlow<String> = _enteredQuery.asStateFlow()

    // Fetch latest releases as soon as ViewModel is created
    init {
        getLatestReleases()
    }

    fun updateResults(query: String) {
        _enteredQuery.value = query

        if (query.isEmpty()) {
            getLatestReleases()
        }
        else {
            searchQuery(query)
        }
    }
    private fun getLatestReleases() {
        viewModelScope.launch {
            try {
                val response = repository.getLatestPopReleases()
                _uiState.value = ListScreenUiState.Success(
                    fetchedAlbums = response)
            } catch (e: Exception) {
                _uiState.value = ListScreenUiState.Error(
                    message = "Error: $e")
            }
        }
    }

    // Function called when user types in the search bar
    private fun searchQuery(query: String) {
        viewModelScope.launch {
            try {
                val albumsObtained = repository.searchAlbum(query)
                val tracksObtained = repository.searchTrack(query)
                _uiState.value = ListScreenUiState.Success(
                    fetchedAlbums = albumsObtained,
                    tracksObtained)
            } catch (e: Exception) {
                _uiState.value = ListScreenUiState.Error(
                    message = "Error: $e")
            }
        }
    }
}