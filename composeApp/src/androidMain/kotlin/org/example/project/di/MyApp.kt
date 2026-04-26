package org.example.project

import android.app.Application
import org.example.project.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.example.project.db.DatabaseDriverFactory
import org.koin.dsl.module
import org.example.project.platform.NetworkMonitor
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.example.project.data.SettingsManager

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp) // [cite: 195] Masukkan context Android
            modules(appModule, module {
                // Platform specific injections [cite: 182]
                single { DatabaseDriverFactory(androidContext()) }
                single { NetworkMonitor(androidContext()) }

                // Settings Manager Injection
                single<Settings> {
                    SharedPreferencesSettings(get<android.content.Context>().getSharedPreferences("app_settings", MODE_PRIVATE))
                }
                single { SettingsManager(get()) }
            })
        }
    }
}