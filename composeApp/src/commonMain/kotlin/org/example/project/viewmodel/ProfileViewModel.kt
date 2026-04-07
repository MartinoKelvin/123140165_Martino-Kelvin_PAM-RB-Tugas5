package com.example.profileapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.profileapp.data.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun toggleDarkMode() {
        _uiState.update {
            it.copy(isDarkMode = !it.isDarkMode)
        }
    }

    fun startEditing() {
        _uiState.update {
            it.copy(isEditing = true)
        }
    }

    fun saveProfile(name: String, bio: String, hobbies: List<String>) {
        _uiState.update {
            it.copy(
                name = name,
                bio = bio,
                hobbies = hobbies,
                isEditing = false
            )
        }
    }


}