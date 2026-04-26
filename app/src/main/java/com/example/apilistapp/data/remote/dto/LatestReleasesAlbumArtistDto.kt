package com.example.apilistapp.data.remote.dto

data class LatestReleasesAlbumArtistDto(
    val id: Long,
    val name: String,
    val tracklist: String,
    val type: String
)