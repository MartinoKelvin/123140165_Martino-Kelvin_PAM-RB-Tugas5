package org.example.project.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.project.data.Note.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import org.example.project.db.NotesDatabase
import org.example.project.db.Note

class NoteRepository(database: NotesDatabase) {
    private val queries = database.noteQueries

    // Get all notes as Flow [cite: 275]
    fun getAllNotes(): Flow<List<Note>> =
        queries.selectAll().asFlow().mapToList(Dispatchers.IO)

    // Fitur Search [cite: 228]
    fun searchNotes(query: String): Flow<List<Note>> =
        queries.search(query, query).asFlow().mapToList(Dispatchers.IO)

    suspend fun insertNote(title: String, content: String) {
        val now = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
        queries.insert(title, content, now)
    }

    suspend fun updateNote(id: Long, title: String, content: String) {
        queries.update(title, content, id)
    }

    suspend fun deleteNote(id: Long) {
        queries.delete(id)
    }
}