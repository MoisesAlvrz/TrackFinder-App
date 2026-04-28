package com.example.apilistapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites")
class AlbumEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val artist: ArtistEntity,
    val coverUrl: String,
    val type: String,
    val releaseDate: String = "Unrecognized",
    val tracks: List<TrackEntity> = emptyList()
)