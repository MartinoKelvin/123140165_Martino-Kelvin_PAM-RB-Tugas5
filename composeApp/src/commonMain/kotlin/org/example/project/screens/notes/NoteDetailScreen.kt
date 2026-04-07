package org.example.project.screens.notes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteDetailScreen(
    noteId: Int,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Note Detail", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(10.dp))

        Text("Note ID: $noteId")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onEdit) {
            Text("Edit Note")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(onClick = onBack) {
            Text("Back")
        }
    }
}