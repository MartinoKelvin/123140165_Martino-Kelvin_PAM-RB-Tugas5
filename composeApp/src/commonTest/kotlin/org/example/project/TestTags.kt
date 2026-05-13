package org.example.project

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.example.project.data.NewsRepository
import org.example.project.viewmodel.NewsViewModel
import org.example.project.viewmodel.NoteViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    single { HttpClient(CIO) } // Client untuk API [cite: 92]
    single { NewsRepository(get()) } // Inject HttpClient ke Repo [cite: 142]
}

val viewModelModule = module {
    viewModel { NewsViewModel(get()) } // Inject Repo ke ViewModel [cite: 89]
    viewModel { NoteViewModel() }
}

val allModules = listOf(dataModule, viewModelModule)