package org.example.project.data
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import org.example.project.db.NotesDatabase
import org.example.project.db.Note
import kotlinx.datetime.Clock

class NoteRepository(database: NotesDatabase) {
    private val queries = database.noteQueries

    fun getAllNotes(): Flow<List<Note>> =
        queries.selectAll().asFlow().mapToList(Dispatchers.IO)

    fun searchNotes(query: String): Flow<List<Note>> =
        queries.search(query, query).asFlow().mapToList(Dispatchers.IO)

    suspend fun insertNote(title: String, content: String) {
        queries.insert(title, content, Clock.System.now().toEpochMilliseconds())
    }

    suspend fun updateNote(id: Long, title: String, content: String) {
        queries.update(title, content, id)
    }

    suspend fun deleteNote(id: Long) {
        queries.delete(id)
    }
}