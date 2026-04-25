package org.example.project.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class SettingsManager(private val settings: Settings) {
    var theme: String
        get() = settings["app_theme", "system"]
        set(value) { settings["app_theme"] = value }

    var sortOrder: String
        get() = settings["sort_order", "newest"]
        set(value) { settings["sort_order"] = value }
}