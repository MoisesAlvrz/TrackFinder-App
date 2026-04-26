package com.example.apilistapp.data.remote.dto

data class LatestReleaseAlbumDto(
    val artist: LatestReleasesAlbumArtistDto,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: Long,
    val md5_image: String,
    val release_date: String,
    val title: String,
    val tracklist: String,
    val type: String
)