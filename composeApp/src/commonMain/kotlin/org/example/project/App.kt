package org.example.project

import androidx.compose.material3.* // Ini akan mengimport darkColorScheme & lightColorScheme
import androidx.compose.runtime.*
import org.example.project.navigation.AppNavigation
import org.example.project.viewmodel.SettingsViewModel
import org.example.project.viewmodel.NoteViewModel

@Composable
fun App(settingsViewModel: SettingsViewModel, noteViewModel: NoteViewModel) {
    // Pastikan import androidx.compose.runtime.getValue agar 'by' tidak error
    val isDarkMode by settingsViewModel.isDarkMode.collectAsState()

    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        // ERROR: "Too many arguments for AppNavigation"
        // Solusi: Kamu harus buka file AppNavigation.kt dan ubah definisinya!
        AppNavigation(noteViewModel, settingsViewModel)
    }
}