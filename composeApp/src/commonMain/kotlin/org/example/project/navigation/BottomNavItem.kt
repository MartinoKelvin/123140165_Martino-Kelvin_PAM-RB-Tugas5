package org.example.project.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings // Tambahkan ini
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.automirrored.filled.List

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object News : BottomNavItem(Screen.News.route, Icons.Default.Newspaper, "News")
    object Notes : BottomNavItem(Screen.Notes.route, Icons.AutoMirrored.Filled.List, "Notes")
    object Favorites : BottomNavItem(Screen.Favorites.route, Icons.Default.Favorite, "Favorites")
    object Profile : BottomNavItem(Screen.Profile.route, Icons.Default.Person, "Profile")
    // Tambahkan ini agar muncul di Navbar:
    object Settings : BottomNavItem("settings_screen", Icons.Default.Settings, "Settings")
}