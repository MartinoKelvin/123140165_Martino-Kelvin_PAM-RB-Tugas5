package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.project.navigation.AppNavigation
import org.example.project.di.allModules // Import allModules yang kita buat tadi

import org.koin.compose.KoinContext
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(allModules)
    }
}

@Composable
fun App() {
    MaterialTheme {
        AppNavigation()
    }
}