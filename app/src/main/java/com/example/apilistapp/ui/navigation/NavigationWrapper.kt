package com.example.apilistapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.components.BottomBar
import com.example.apilistapp.ui.components.TopBar
import com.example.apilistapp.ui.screens.detail.DetailScreen
import com.example.apilistapp.ui.screens.favorites.FavoritesScreen
import com.example.apilistapp.ui.screens.list.ListScreen
import com.example.apilistapp.ui.screens.settings.SettingsScreen

@Composable
fun NavigationWrapper() {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(Routes.ListScreen)
    val currentScreen: NavKey? = backStack.lastOrNull()

    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = currentScreen,
                onNavigate = { selectedRoute ->
                    if (currentScreen != selectedRoute) {
                        backStack.add(selectedRoute)
                    }
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            modifier = Modifier.padding(innerPadding),
            entryProvider = entryProvider {
                entry<Routes.ListScreen> {
                    ListScreen() { clickedId ->
                        backStack.add(Routes.DetailScreen(releaseId = clickedId))
                    }
                }
                entry<Routes.DetailScreen> { key ->
                    DetailScreen(key.releaseId) {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }
                    }
                }
                entry<Routes.FavoritesScreen> {
                    FavoritesScreen {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }
                    }
                }
                entry<Routes.SettingsScreen> {
                    SettingsScreen() {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }
                    }
                }
            }
        )
    }
}