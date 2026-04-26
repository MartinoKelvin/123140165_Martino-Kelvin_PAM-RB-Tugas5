package org.example.project.di

import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.example.project.data.NoteRepository
import org.example.project.viewmodel.NoteViewModel
import org.example.project.viewmodel.SettingsViewModel
import org.example.project.platform.DeviceInfo

val appModule = module {
    singleOf(::NoteRepository)
    singleOf(::DeviceInfo)

    // Mendaftarkan ViewModel menggunakan pola constructor injection
    viewModelOf(::NoteViewModel)
    viewModelOf(::SettingsViewModel)
}