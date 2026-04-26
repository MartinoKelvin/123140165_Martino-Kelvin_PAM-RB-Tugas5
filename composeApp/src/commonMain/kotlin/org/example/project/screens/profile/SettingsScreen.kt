package org.example.project.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.viewmodel.SettingsViewModel
import org.example.project.platform.DeviceInfo
import org.koin.compose.koinInject // WAJIB ADA INI [cite: 239, 561]
import org.koin.compose.viewmodel.koinViewModel // WAJIB ADA INI [cite: 233, 560]

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    // Inject dependencies secara otomatis [cite: 242, 557]
    val viewModel: SettingsViewModel = koinViewModel()
    val deviceInfo: DeviceInfo = koinInject()

    val isDarkMode by viewModel.isDarkMode.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            // Menampilkan Device Info sesuai tugas Pertemuan 8 [cite: 503, 505]
            Text("Device Information", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Model: ${deviceInfo.getDeviceName()}")
                    Text("OS: ${deviceInfo.getOsVersion()}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Appearance", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Dark Mode")
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { viewModel.toggleTheme(it) }
                )
            }
        }
    }
}