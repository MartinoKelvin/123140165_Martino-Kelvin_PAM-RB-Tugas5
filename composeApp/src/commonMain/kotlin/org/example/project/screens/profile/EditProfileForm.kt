package org.example.project.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EditProfileForm(
    currentName: String,
    currentBio: String,
    currentHobbies: List<String>,
    onSave: (String, String, List<String>) -> Unit
) {

    var name by remember { mutableStateOf(currentName) }
    var bio by remember { mutableStateOf(currentBio) }

    var hobbiesText by remember {
        mutableStateOf(currentHobbies.joinToString(", "))
    }

    Column(modifier = Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Bio") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = hobbiesText,
            onValueChange = { hobbiesText = it },
            label = { Text("Hobbies (pisahkan dengan koma)") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1565C0),
                contentColor = Color.White
            ),
            onClick = {
                val hobbies = hobbiesText.split(",").map { it.trim() }
                onSave(name, bio, hobbies)
            }
        ) {
            Text("Save")
        }
    }
}