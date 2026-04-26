package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import com.example.project.data.Note.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.data.NoteRepository

class NoteViewModel() : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    fun addNote(title: String, color: Long) {
        val newId = if (_notes.value.isEmpty()) 1 else _notes.value.maxOf { it.id } + 1
        val newNote = Note(id = newId, title = title, cardColor = color)
        _notes.value = _notes.value + newNote
    }

    fun toggleFavorite(noteId: Int) {
        _notes.value = _notes.value.map {
            if (it.id == noteId) it.copy(isFavorite = !it.isFavorite) else it
        }
    }
}