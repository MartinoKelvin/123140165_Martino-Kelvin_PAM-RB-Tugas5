import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "1.9.21"
    id("app.cash.sqldelight") version "2.0.1"
}

kotlin {
    androidTarget()

    // DEKLARASIKAN TARGET iOS DI SINI (Sebelum sourceSets)
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    val ktorVersion = "2.3.7"
    val sqlDelightVersion = "2.0.1"

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation("io.ktor:ktor-client-android:${ktorVersion}")
            implementation("app.cash.sqldelight:android-driver:$sqlDelightVersion")
        }

        // PERBAIKAN: Gunakan deklarasi yang benar untuk iOS [cite: 183, 187]
        val iosMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation("app.cash.sqldelight:native-driver:$sqlDelightVersion")
            }
        }

        // Menghubungkan target spesifik ke iosMain agar tidak error
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        iosArm64Main.dependsOn(iosMain)
        iosSimulatorArm64Main.dependsOn(iosMain)

        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // UI & Icons
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.7.0-alpha07")

            // Networking [cite: 27]
            implementation("io.ktor:ktor-client-core:${ktorVersion}")
            implementation("io.ktor:ktor-client-content-negotiation:${ktorVersion}")
            implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")

            // SQLDelight [cite: 180, 181]
            implementation("app.cash.sqldelight:runtime:$sqlDelightVersion")
            implementation("app.cash.sqldelight:coroutines-extensions:$sqlDelightVersion")

            // Settings (DataStore pengganti) [cite: 80]
            implementation("com.russhwolf:multiplatform-settings:1.1.1")
            implementation("com.russhwolf:multiplatform-settings-coroutines:1.1.1")

            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildToolsVersion = "35.0.0"
}

// SETUP DATABASE SQLDELIGHT [cite: 191, 193, 194]
sqldelight {
    databases {
        create("NotesDatabase") {
            packageName.set("org.example.project.db")
        }
    }
}