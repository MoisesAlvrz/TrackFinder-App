package com.example.apilistapp.data.mapper

import com.example.apilistapp.data.remote.dto.AlbumDetailsDto
import com.example.apilistapp.data.remote.dto.AlbumDetailsTrackAlbumDto
import com.example.apilistapp.data.remote.dto.LatestReleaseAlbumDto
import com.example.apilistapp.data.remote.dto.LatestReleasesAlbumArtistDto
import com.example.apilistapp.data.remote.dto.SearchAlbumsAlbumDto
import com.example.apilistapp.data.remote.dto.SearchAlbumsListDto
import com.example.apilistapp.domain.Album

fun LatestReleaseAlbumDto.toDomain(): Album {
    return Album(
        id = id,
        title = title,
        artist = artist.toDomain(),
        coverUrl = cover_big ?: "",
        type = type,
        releaseDate = release_date
    )
}

fun AlbumDetailsTrackAlbumDto.toDomain(albumArtist: LatestReleasesAlbumArtistDto): Album {
    return Album(
        id = id,
        title = title,
        artist = albumArtist.toDomain(),
        coverUrl = cover_big ?: "",
        type = type,
        releaseDate = "Undefined"

    )
}

fun AlbumDetailsDto.toDomain(): Album {
    return Album(
        id = id,
        title = title,
        artist = artist.toDomain(),
        coverUrl = cover_big ?: "",
        type = type,
        releaseDate = release_date,
        tracks = tracks.data.map { trackDto ->
            trackDto.toDomain(id) }
    )
}

fun SearchAlbumsAlbumDto.toDomain() : Album {
    return Album(
        id = id,
        title = title,
        artist = artist.toDomain(),
        coverUrl = cover_big ?: "",
        type = type
    )
}