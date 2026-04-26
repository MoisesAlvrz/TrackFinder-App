package com.example.apilistapp.data.remote

import com.example.apilistapp.data.remote.dto.AlbumDetailsDto
import com.example.apilistapp.data.remote.dto.SearchAlbumsListDto
import com.example.apilistapp.data.remote.dto.LatestReleasesListDto
import com.example.apilistapp.data.remote.dto.SearchTracksListDto
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Latest pop releases:
// https://api.deezer.com/editorial/0/releases
// Search albums:
// https://api.deezer.com/search/album/?q=Born_To_Die
// Search tracks:
// https://api.deezer.com/search/?q=ilomilo
// Album Details:
// https://api.deezer.com/album/1503218
interface DeezerApiInterface {

    // Fetch latest pop releases
    @GET("editorial/0/releases")
    suspend fun getLatestPopReleases(): Response<LatestReleasesListDto>

    // Search tracks
    @GET("search")
    suspend fun searchTrack(
        @Query("q")
        query: String
    ): Response<SearchTracksListDto>

    // Search albums
    @GET("search/album")
    suspend fun searchAlbum(
        @Query("q")
        query: String
    ): Response<SearchAlbumsListDto>

    @GET("album/{id}")
    suspend fun getAlbumDetailsById(
        @Path("id")
        id: Long
    ): Response<AlbumDetailsDto>

    // CLIENT FOR CONNECTION
    companion object {
        // Define base URL
        const val BASE_URL = "https://api.deezer.com/"

        fun create(): DeezerApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(DeezerApiInterface::class.java)
        }
    }
}