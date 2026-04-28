package com.example.apilistapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apilistapp.domain.Artist

@Entity(tableName = "Tracks")
class TrackEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val artist: Artist,
    val albumId: Long
)