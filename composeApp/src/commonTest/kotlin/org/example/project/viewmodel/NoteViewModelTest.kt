package org.example.project.viewmodel

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class NoteViewModelTest {

    @Test
    fun `initial notes should be empty`() = runTest {
        val viewModel = NoteViewModel()

        // Menggunakan Turbine untuk cek initial state [cite: 280]
        viewModel.notes.test {
            val items = awaitItem()
            assertTrue(items.isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `addNote should increase list size and generate correct ID`() = runTest {
        val viewModel = NoteViewModel()
        val title = "Belajar PAM"
        val color = 0xFFFFFFL

        // Act
        viewModel.addNote(title, color)

        // Assert
        viewModel.notes.test {
            val notes = awaitItem()
            assertEquals(1, notes.size)
            assertEquals(1, notes[0].id) // ID pertama harus 1
            assertEquals(title, notes[0].title)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `addNote multiple times should increment IDs correctly`() = runTest {
        val viewModel = NoteViewModel()

        // Act
        viewModel.addNote("Note 1", 0L)
        viewModel.addNote("Note 2", 0L)

        // Assert
        viewModel.notes.test {
            val notes = awaitItem()
            assertEquals(2, notes.size)
            assertEquals(1, notes[0].id)
            assertEquals(2, notes[1].id) // Verifikasi auto-increment ID
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `toggleFavorite should change isFavorite status`() = runTest {
        val viewModel = NoteViewModel()
        viewModel.addNote("Test Note", 0L)
        val noteId = 1

        // Act & Assert
        viewModel.notes.test {
            var notes = awaitItem()
            assertFalse(notes[0].isFavorite) // Awalnya false

            viewModel.toggleFavorite(noteId)

            notes = awaitItem()
            assertTrue(notes[0].isFavorite) // Jadi true

            cancelAndIgnoreRemainingEvents()
        }
    }
}