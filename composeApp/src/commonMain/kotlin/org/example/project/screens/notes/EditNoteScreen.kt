package org.example.project.screens.notes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditNoteScreen(
    noteId: Int,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("Note $noteId") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Edit Note", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Editing note ID: $noteId")

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Edit Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBack) {
            Text("Save Changes")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(onClick = onBack) {
            Text("Back")
        }
    }
}