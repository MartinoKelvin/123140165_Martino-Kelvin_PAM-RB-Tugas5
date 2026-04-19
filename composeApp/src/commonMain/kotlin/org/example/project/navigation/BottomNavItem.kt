package org.example.project.navigation

import Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.automirrored.filled.List

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    // Di dalam sealed class BottomNavItem
    object News : BottomNavItem(Screen.News.route, Icons.Default.Newspaper, "News")

    object Notes : BottomNavItem(Screen.Notes.route, Icons.AutoMirrored.Filled.List, "Notes")
    object Favorites : BottomNavItem(Screen.Favorites.route, Icons.Default.Favorite, "Favorites")
    object Profile : BottomNavItem(Screen.Profile.route, Icons.Default.Person, "Profile")
}