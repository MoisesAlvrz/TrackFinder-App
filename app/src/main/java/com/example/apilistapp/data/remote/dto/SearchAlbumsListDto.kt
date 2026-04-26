package com.example.apilistapp.data.remote.dto

data class SearchAlbumsListDto(
    val `data`: List<SearchAlbumsAlbumDto>,
    val next: String,
    val total: Int
)