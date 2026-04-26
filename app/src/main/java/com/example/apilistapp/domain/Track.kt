package com.example.apilistapp.domain

data class Track (
    val id: Long,
    val title: String,
    val artist: Artist,
    // Storing album reference as ID instead of object to save memory
    // and avoid circular dependency: Album -> Track -> Album -> Track...
    val albumId: Long
)