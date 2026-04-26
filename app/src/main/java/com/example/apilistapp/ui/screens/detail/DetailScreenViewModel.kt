package com.example.apilistapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.repository.ApiRepository
import com.example.apilistapp.domain.Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DetailScreenViewModel: ViewModel() {
    // Instance repository
    private val repository = ApiRepository()

    // Store
    private val _albumDetails = MutableStateFlow<Album?>(null)
    val albumDetails: StateFlow<Album?> = _albumDetails.asStateFlow()

    fun getAlbumDetails(albumId: Long) {
        viewModelScope.launch {
            try {
                val response = repository.getAlbumDetails(albumId)
                _albumDetails.value = response
            } catch (e: Exception) {
                println("Error: $e")
            }
        }
    }
}