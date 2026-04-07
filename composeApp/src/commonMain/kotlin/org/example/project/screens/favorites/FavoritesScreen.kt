package org.example.project.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.example.project.screens.notes.NoteCard


import org.example.project.viewmodel.NoteViewModel

@Composable
fun FavoritesScreen(viewModel: NoteViewModel) {
    // Mengambil data notes dari StateFlow yang sama [cite: 44, 491]
    val notes by viewModel.notes.collectAsState()

    // Filter hanya note yang isFavorite == true [cite: 506, 507]
    val favoriteNotes = notes.filter { it.isFavorite }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0)) // Background Orange yang sama
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Header Favorites
        Text(
            text = "Favorite Notes",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteNotes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Belum ada catatan favorit.\nTekan ikon hati di daftar catatan.",
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            // Menggunakan LazyColumn agar performa tetap ringan [cite: 524]
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(favoriteNotes) { note ->
                    // Memanggil NoteCard yang sama agar desain seragam
                    NoteCard(
                        title = note.title,
                        isFavorite = note.isFavorite,
                        cardColor = Color(note.cardColor),
                        onClick = { /* Navigasi ke detail jika perlu */ },
                        onFavoriteClick = { viewModel.toggleFavorite(note.id) }
                    )
                }
            }
        }
    }
}