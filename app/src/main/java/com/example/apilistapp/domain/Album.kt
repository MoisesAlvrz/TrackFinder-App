package com.example.apilistapp.domain

data class Album(
    val id: Long,
    val title: String,
    val artist: Artist,
    val coverUrl: String,
    val type: String,
    val releaseDate: String = "Unrecognized",
    // Tracks will only be fetched when needed.
    val tracks: List<Track> = emptyList()
)