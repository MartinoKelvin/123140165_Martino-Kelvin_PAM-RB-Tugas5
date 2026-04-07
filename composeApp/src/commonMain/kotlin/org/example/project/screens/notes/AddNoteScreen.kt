package org.example.project.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.viewmodel.NoteViewModel
@Composable
fun AddNoteScreen(
    viewModel: NoteViewModel,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    val colorOptions = listOf(0xFFFFCC80, 0xFFFFAB91, 0xFFFFE082, 0xFFE6EE9C)
    var selectedColor by remember { mutableStateOf(colorOptions[0]) }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF3E0)).padding(16.dp)) {
        Text("Add Note", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text("Pilih Warna Kartu:")
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            colorOptions.forEach { color ->
                Box(
                    modifier = Modifier.size(45.dp).padding(4.dp).clip(CircleShape)
                        .background(Color(color))
                        .clickable { selectedColor = color }
                        .border(if (selectedColor == color) 2.dp else 0.dp, Color.Black, CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (title.isNotBlank()) {
                    viewModel.addNote(title, selectedColor)
                    onBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Save") }
    }
}