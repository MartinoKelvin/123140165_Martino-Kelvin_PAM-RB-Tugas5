package org.example.project.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.profileapp.viewmodel.ProfileViewModel
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Image
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import pertemuan5_navigasi.composeapp.generated.resources.Res
import pertemuan5_navigasi.composeapp.generated.resources.hero




@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {

    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color(0xFF1565C0))
        ) {

            Icon(
                imageVector = if (state.isDarkMode)
                    Icons.Filled.DarkMode
                else
                    Icons.Filled.LightMode,
                contentDescription = "Toggle Theme",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(30.dp)
                    .size(28.dp)
                    .clickable {
                        viewModel.toggleDarkMode()
                    }
            )

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(Res.drawable.hero),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = state.name,
                    color = Color.White,
                    fontSize = 20.sp
                )

                Text(
                    text = state.bio,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {

            Column(modifier = Modifier.padding(16.dp)) {
                state.hobbies.forEachIndexed { index, hobby ->

                    Text(hobby)

                    if (index < state.hobbies.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (state.isEditing) {

            EditProfileForm(
                currentName = state.name,
                currentBio = state.bio,
                currentHobbies = state.hobbies,
                onSave = { name, bio, hobbies ->
                    viewModel.saveProfile(name, bio, hobbies)
                }
            )

        } else {

            Button(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1565C0),
                    contentColor = Color.White
                ),
                onClick = {
                    viewModel.startEditing()
                }
            ) {
                Text("Edit Profile")
            }

        }
    }
}