package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.SettingsManager

class SettingsViewModel(private val settingsManager: SettingsManager) : ViewModel() {

    // Ambil initial value dari SettingsManager (DataStore)
    private val _isDarkMode = MutableStateFlow(settingsManager.theme == "dark")
    val isDarkMode = _isDarkMode.asStateFlow()

    fun toggleTheme(dark: Boolean) {
        viewModelScope.launch {
            _isDarkMode.value = dark
            settingsManager.theme = if (dark) "dark" else "light"
        }
    }
}