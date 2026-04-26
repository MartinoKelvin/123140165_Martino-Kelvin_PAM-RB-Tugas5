package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings // TAMBAHKAN INI
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

        // 2. Inisialisasi Settings (Cara paling aman untuk Android)
        val sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE)
        val settings: Settings = SharedPreferencesSettings(sharedPreferences)
        val settingsManager = SettingsManager(settings)

        // 3. Inisialisasi ViewModels
        // Jika NoteViewModel masih merah, pastikan di file NoteViewModel.kt
        // sudah ada constructor: class NoteViewModel(private val repository: NoteRepository)
        val noteVM = NoteViewModel()
        val settingsVM = SettingsViewModel(settingsManager)

        setContent {
            App(settingsVM, noteVM)
        }
    }
}