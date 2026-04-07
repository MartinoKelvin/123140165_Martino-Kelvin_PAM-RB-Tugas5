package com.example.profileapp.data

data class ProfileUiState(
    val name: String = "Martino Kelvin",
    val bio: String = "Mahasiswa Informatika ITERA",
    val hobbies: List<String> = listOf("Coding", "Gym", "Gaming"),
    val isDarkMode: Boolean = false,
    val isEditing: Boolean = false
)