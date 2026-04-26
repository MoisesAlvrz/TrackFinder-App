package com.example.apilistapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.apilistapp.domain.Artist
import com.example.apilistapp.domain.Track
import com.example.apilistapp.ui.components.TopBar

@Composable
fun DetailScreen(releaseId: Long, navigateBack: () -> Unit) {
    val vm: DetailScreenViewModel = viewModel()
    val albumDetails by vm.albumDetails.collectAsStateWithLifecycle()

    val colors = MaterialTheme.colorScheme

    LaunchedEffect(releaseId) {
        vm.getAlbumDetails(releaseId)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.Red),
    ) {
        TopBar(screenLabel = "Album Details", onBack = navigateBack)

        if (albumDetails == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(

                ) { }
                LazyColumn(
                    Modifier.padding(vertical = 5.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        AlbumCover(albumDetails?.coverUrl ?: "")
                        AlbumHeader(
                            albumDetails!!.title,
                            albumDetails!!.releaseDate,
                            albumDetails!!.artist
                        )
                    }

                    item {
                        Text("Tracklist")
                    }

                    items(albumDetails?.tracks ?: emptyList()) { item ->
                        TrackItem(track = item)
                    }
                }
            }
        }
    }

}

@Composable
fun AlbumHeader(albumTitle: String, albumReleaseDate: String, albumArtist: Artist) {
    Column(
        modifier = Modifier.padding(vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = albumTitle, fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Text(text = albumArtist.name, fontSize = 22.sp)
        Text(text = albumReleaseDate, fontWeight = FontWeight.Light, fontSize = 12.sp)
    }
}

@Composable
fun TrackItem(track: Track) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(

        ) {
            Text(
                text = track.title,
                fontSize = 16.sp,
            )
            Text(
                text = track.artist.name,
                fontSize = 16.sp
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AlbumCover(coverUrl: String) {
    GlideImage(
        model = coverUrl,
        contentDescription = "Album cover",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}