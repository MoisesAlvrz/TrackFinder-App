package com.example.apilistapp.ui.screens.list

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.apilistapp.domain.Album
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun ListScreen(navigateToDetail: (Long) -> Unit) {
    val vm: ListScreenViewModel = viewModel()
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val enteredQuery by vm.enteredQuery.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        AppLogo(modifier = Modifier.padding(vertical = 64.dp))
        SearchBar(enteredQuery,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
            onQueryChange = { vm.updateResults(it) })

        when (val currentState = uiState) {
            is ListScreenUiState.Loading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ListScreenUiState.Error -> {
                Text(text = (currentState.message))
            }

            is ListScreenUiState.Success -> {
                val albumsList = currentState.fetchedAlbums
                val tracksList = currentState.fetchedTracks

                if (tracksList.isNotEmpty()) {

                }
                LazyColumn(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(albumsList) { release ->
                        ReleaseItem(release = release) { navigateToDetail(release.id) }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search...") },
        // Magnifying glass icon.
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        // Rounded edges.
        shape = RoundedCornerShape(50.dp),
        // Remove default underline.
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ReleaseItem(release: Album, onClick: (Long) -> Unit) {
    Card(
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(release.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReleaseCover(release)
            ReleaseText(release)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ReleaseCover(releaseItem: Album) {
    GlideImage(
        model = releaseItem.coverUrl,
        contentDescription = "Album cover",
        modifier = Modifier.size(90.dp)
    )
}

@Composable
fun ReleaseText(releaseItem: Album) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            releaseItem.title, maxLines = 1, fontSize = 22.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.basicMarquee(
                iterations = Int.MAX_VALUE,
                repeatDelayMillis = 2000,
                velocity = 30.dp
            )
        )
        Text(text = releaseItem.artist.name)
    }
}

@Composable
fun AppLogo(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                append("TRACK")
            }
            withStyle(SpanStyle(color = Color(0xFF24AE74))) {
                append("FINDER")
            }
        },
        fontSize = 28.sp,
        letterSpacing = 2.sp,
        fontWeight = FontWeight.Light,
    )
}