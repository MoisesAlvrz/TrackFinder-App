package com.example.apilistapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.example.apilistapp.ui.navigation.Routes

@Composable
fun BottomBar(
    currentScreen: NavKey?,
    onNavigate: (Routes) -> Unit
) {
    Column {
        HorizontalDivider(thickness = 2.dp, color = Color.Black)
        NavigationBar {
            NavigationBarItem(
                icon = { Icon(Icons.Default.List, contentDescription = "List") },
                label = { Text("Explorer") },
                selected = currentScreen is Routes.ListScreen,
                onClick = { onNavigate(Routes.ListScreen) }
            )

            NavigationBarItem(
                icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                label = { Text("Favorites") },
                selected = currentScreen is Routes.FavoritesScreen,
                onClick = { onNavigate(Routes.FavoritesScreen) }
            )

            NavigationBarItem(
                icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                label = { Text("Settings") },
                selected = currentScreen is Routes.SettingsScreen,
                onClick = { onNavigate(Routes.SettingsScreen) }
            )
        }
    }
}