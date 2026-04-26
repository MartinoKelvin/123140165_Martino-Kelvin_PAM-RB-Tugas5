package org.example.project

import androidx.compose.material3.*
import androidx.compose.runtime.*
import org.example.project.navigation.AppNavigation
import org.example.project.viewmodel.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    // Inject SettingsViewModel secara otomatis melalui Koin
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val isDarkMode by settingsViewModel.isDarkMode.collectAsState()

    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        // AppNavigation dipanggil tanpa parameter manual
        AppNavigation()
    }
}