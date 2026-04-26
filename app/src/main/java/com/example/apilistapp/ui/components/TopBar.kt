package com.example.apilistapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(screenLabel: String = "", onBack: () -> Unit) {
    val colors = MaterialTheme.colorScheme

    Row(
        Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(colors.background)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        Box(
            Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = screenLabel,
                fontSize = 18.sp
            )
        }
        Box(
            Modifier
                .size(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                        append("T")
                    }
                    withStyle(SpanStyle(color = colors.onPrimary)) {
                        append("F")
                    }
                },
                fontSize = 18.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Light
            )
        }

    }
}