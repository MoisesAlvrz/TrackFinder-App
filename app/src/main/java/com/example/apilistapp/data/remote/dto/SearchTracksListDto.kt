package com.example.apilistapp.data.remote.dto

data class SearchTracksListDto(
    val `data`: List<SearchTracksTrackDto>,
    val next: String,
    val total: Int
)