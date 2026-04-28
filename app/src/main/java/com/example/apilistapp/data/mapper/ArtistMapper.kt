package com.example.apilistapp.data.mapper

import com.example.apilistapp.data.remote.dto.AlbumDetailsArtistDto
import com.example.apilistapp.data.remote.dto.AlbumDetailsTrackArtistDto
import com.example.apilistapp.data.remote.dto.LatestReleasesAlbumArtistDto
import com.example.apilistapp.data.remote.dto.SearchAlbumsArtistDto
import com.example.apilistapp.data.remote.dto.SearchTracksArtistDto
import com.example.apilistapp.domain.Artist

fun LatestReleasesAlbumArtistDto.toDomain(): Artist {
    return Artist(
        id = id,
        name = name
    )
}

fun AlbumDetailsArtistDto.toDomain(): Artist {
    return Artist(
        id = id,
        name = name,
        pictureUrl = picture_medium
    )
}

fun AlbumDetailsTrackArtistDto.toDomain(): Artist {
    return Artist(
        id = id,
        name = name
    )
}

fun SearchAlbumsArtistDto.toDomain(): Artist {
    return Artist(
        id = id,
        name = name
    )
}

fun SearchTracksArtistDto.toDomain(): Artist {
    return Artist(
        id = id,
        name = name
    )
}