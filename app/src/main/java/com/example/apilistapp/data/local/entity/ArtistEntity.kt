package com.example.apilistapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Artists")
class ArtistEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val pictureUrl: String = "not set"
)