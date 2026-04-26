package org.example.project.platform

import android.os.Build

actual class DeviceInfo {
    actual fun getDeviceName(): String = Build.MODEL
    actual fun getOsVersion(): String = "Android ${Build.VERSION.RELEASE}"
}