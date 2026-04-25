package org.example.project
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.russhwolf.settings.Settings
import org.example.project.data.*
import org.example.project.db.*
import org.example.project.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val driver = DatabaseDriverFactory(applicationContext).createDriver()
        val database = NotesDatabase(driver)
        val noteRepo = NoteRepository(database)
        val settingsManager = SettingsManager(Settings())

        val notesVM = NotesViewModel(noteRepo)
        val settingsVM = SettingsViewModel(settingsManager)

        setContent {
            App(settingsVM, notesVM)
        }
    }
}