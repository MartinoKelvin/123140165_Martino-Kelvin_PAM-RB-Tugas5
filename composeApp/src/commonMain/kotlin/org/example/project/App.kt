package org.example.project
import androidx.compose.material3.*
import androidx.compose.runtime.*
import org.example.project.navigation.AppNavigation
import org.example.project.viewmodel.SettingsViewModel
import org.example.project.viewmodel.NotesViewModel

@Composable
fun App(settingsViewModel: SettingsViewModel, notesViewModel: NotesViewModel) {
    val isDarkMode by settingsViewModel.isDarkMode.collectAsState()

    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        AppNavigation(notesViewModel, settingsViewModel)
    }
}