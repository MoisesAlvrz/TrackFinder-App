package com.example.apilistapp.ui.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.apilistapp.ui.components.TopBar

@Composable
fun SettingsScreen(navigateBack: () -> Unit) {
    val databaseAdded = false
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar("Settings", onBack = navigateBack)
        if (!databaseAdded) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Settings Screen")
            }
        }
    }
}
