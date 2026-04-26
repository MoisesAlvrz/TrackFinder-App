package com.example.apilistapp.data.repository

import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.remote.DeezerApiInterface
import com.example.apilistapp.domain.Album
import com.example.apilistapp.domain.Track

class ApiRepository {
    // Instantiate API interface
    val apiInterface: DeezerApiInterface = DeezerApiInterface.create()

    // Create functions that call interface methods.
    suspend fun getLatestPopReleases(): MutableList<Album> {
        val albumList: MutableList<Album> = mutableListOf()
        val response = apiInterface.getLatestPopReleases()
        if (response.isSuccessful) {
            response.body()!!.data.forEach {
                albumList.add( it.toDomain() )
            }
        }
        return albumList
    }

    suspend fun getAlbumDetails(albumId: Long): Album? {
        val response = apiInterface.getAlbumDetailsById(albumId)

        return if (response.isSuccessful) {
            response.body()?.toDomain()
        } else {
            null
        }
    }

    suspend fun searchAlbum(query: String): List<Album> {
        var albumList: MutableList<Album> = mutableListOf()
        val response = apiInterface.searchAlbum(query)

        if (response.isSuccessful) {
            response.body()!!.data.forEach {
                albumList.add( it.toDomain() )
            }
        }

        return  albumList
    }

    suspend fun searchTrack(query: String): List<Track> {
        var trackList: MutableList<Track> = mutableListOf()
        val response = apiInterface.searchTrack(query)

        if (response.isSuccessful) {
            response.body()!!.data.forEach {
                trackList.add( it.toDomain() )
            }
        }

        return  trackList
    }
}