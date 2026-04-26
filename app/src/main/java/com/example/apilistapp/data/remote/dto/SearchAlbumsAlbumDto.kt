package com.example.apilistapp.data.remote.dto

data class SearchAlbumsAlbumDto(
    val artist: SearchAlbumsArtistDto,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val genre_id: Int,
    val id: Long,
    val link: String,
    val md5_image: String,
    val nb_tracks: Int,
    val record_type: String,
    val title: String,
    val tracklist: String,
    val type: String
)