package org.example.project.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Settings // Tambahkan import ini
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val label: String) {

    object News : Screen("news", Icons.Default.Newspaper, "News")
    object Notes : Screen("notes", Icons.Default.Newspaper, "Notes")
    object Favorites : Screen("favorites", Icons.Default.Newspaper, "Favorites")
    object Profile : Screen("profile", Icons.Default.Newspaper, "Profile")

    // TAMBAHKAN INI UNTUK TUGAS MINGGU 7
    object Settings : Screen("settings_screen", Icons.Default.Settings, "Settings")

    object NoteDetail : Screen("note_detail/{noteId}", Icons.Default.Newspaper, "Note Detail") {
        fun createRoute(noteId: Int) = "note_detail/$noteId"
    }

    object AddNote : Screen("add_note", Icons.Default.Newspaper, "Add Note")

    object EditNote : Screen("edit_note/{noteId}", Icons.Default.Newspaper, "Edit Note") {
        fun createRoute(noteId: Int) = "edit_note/$noteId"
    }
}