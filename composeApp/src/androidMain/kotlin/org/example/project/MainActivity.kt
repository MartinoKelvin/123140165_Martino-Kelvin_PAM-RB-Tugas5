package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.russhwolf.settings.Settings
import org.example.project.data.NoteRepository
import org.example.project.data.SettingsManager
import org.example.project.db.DatabaseDriverFactory
import org.example.project.db.NotesDatabase
import org.example.project.viewmodel.NoteViewModel
import org.example.project.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi Database (SQLDelight)
        val driver = DatabaseDriverFactory(applicationContext).createDriver()
        val database = NotesDatabase(driver)
        val noteRepo = NoteRepository(database)

        // 2. Inisialisasi Settings (DataStore)
        val settings: Settings = Settings()
        val settingsManager = SettingsManager(settings)

        // 3. Inisialisasi ViewModels dengan constructor yang sudah diperbaiki
        val noteVM = NoteViewModel(noteRepo)
        val settingsVM = SettingsViewModel(settingsManager)

        setContent {
            // Pastikan fungsi App di App.kt menerima parameter ini
            App(settingsVM, noteVM)
        }
    }
}