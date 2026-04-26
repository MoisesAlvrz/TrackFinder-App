package com.example.apilistapp.data.remote.dto

data class LatestReleasesListDto(
    val `data`: List<LatestReleaseAlbumDto>,
    val next: String,
    val total: Int
)