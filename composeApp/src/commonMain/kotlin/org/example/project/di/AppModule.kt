package org.example.project.di

// 1. HAPUS import androidx.lifecycle.viewmodel.compose.viewModel
// 2. GUNAKAN import di bawah ini:

import org.koin.dsl.module
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.project.data.NewsRepository
import org.example.project.viewmodel.NewsViewModel
import org.example.project.viewmodel.NoteViewModel
import org.koin.compose.viewmodel.dsl.viewModel

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
    // get() di sini akan mengambil HttpClient di atas [cite: 142]
    single { NewsRepository(get()) }
}

val viewModelModule = module {
    // Fungsi viewModel { } di sini sekarang benar milik Koin [cite: 89, 109]
    viewModel { NewsViewModel(get()) }
    viewModel { NoteViewModel() }
}

val allModules = listOf(dataModule, viewModelModule)