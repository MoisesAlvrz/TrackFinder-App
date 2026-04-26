package com.example.apilistapp.data.mapper

import com.example.apilistapp.data.remote.dto.AlbumDetailsTracksTrackDto
import com.example.apilistapp.data.remote.dto.SearchTracksTrackDto
import com.example.apilistapp.domain.Album
import com.example.apilistapp.domain.Track

fun AlbumDetailsTracksTrackDto.toDomain(parentAlbumId: Long): Track {
    return Track(
        id = id,
        title = title,
        artist = artist.toDomain(),
        albumId = parentAlbumId
    )
}

fun SearchTracksTrackDto.toDomain(): Track {
    return Track(
        id = id,
        title = title,
        artist = artist.toDomain(),
        albumId = album.id
    )
}
