
package com.example.project.data.Note

import androidx.compose.ui.graphics.Color
data class Note(
    val id: Int,
    val title: String,
    val content: String = "",
    var isFavorite: Boolean = false, // Fitur favorit
    val cardColor: Long = 0xFFFFCC80
)