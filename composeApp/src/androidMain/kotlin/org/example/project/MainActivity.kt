package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // [cite: 203] Karena sudah pakai Koin, inisialisasi di MainActivity jadi bersih
            App()
        }
    }
}