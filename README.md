# NoteApp - Platform-Specific Features (Tugas Minggu 8)

Proyek ini adalah aplikasi manajemen catatan berbasis **Kotlin Multiplatform (KMP)** yang diimplementasikan untuk memenuhi tugas Pertemuan 8 mata kuliah Pengembangan Aplikasi Mobile. Fokus utama tugas ini adalah penerapan pola **expect/actual**, **Dependency Injection (DI)** menggunakan Koin, dan integrasi **Platform APIs**.

## 👤 Identitas Mahasiswa
- **Nama:** Martino Kelvin
- **NIM:** 121140xxx (Sesuaikan NIM kamu)
- **Program Studi:** Teknik Informatika
- **Instansi:** Institut Teknologi Sumatera (ITERA)

---

## 🚀 Fitur & Implementasi (Minggu 8)

1. **Dependency Injection (Koin):** Seluruh manajemen objek seperti Repository, ViewModel, dan Platform Services dikelola melalui Koin DI untuk meningkatkan modularitas dan testability.
2. **Platform-Specific Info:** Implementasi pola `expect/actual` untuk mengambil informasi hardware perangkat (Model & OS) secara native.
3. **Network Status Monitoring:** Deteksi status koneksi internet secara real-time menggunakan `ConnectivityManager` di Android.
4. **UI Integration:** Menampilkan informasi perangkat di halaman Settings dan indikator jaringan di halaman utama.

---

## 🏗️ Arsitektur & Struktur Proyek

Aplikasi ini memisahkan kode antara logika bisnis (Shared) dan implementasi spesifik platform.



### Struktur Folder Penting:
- **`commonMain/di/AppModule.kt`**: Konfigurasi Koin Module menggunakan `viewModelOf` dan `singleOf`.
- **`commonMain/platform/`**: Deklarasi `expect class` untuk DeviceInfo dan NetworkMonitor.
- **`androidMain/platform/`**: Implementasi `actual` menggunakan Android API seperti `Build.MODEL` dan `ConnectivityManager`.
- **`androidMain/MyApp.kt`**: Inisialisasi `startKoin` dengan `androidContext`.

---

## 📸 Screenshots Implementation

| **Device Information (Settings Screen)** | **Network Status Indicator** |
|:---:|:---:|
| ![Device Info Screenshot](ISI_LINK_GAMBAR_DISINI) | ![Network Indicator Screenshot](ISI_LINK_GAMBAR_DISINI) |
| *Menunjukkan Model Perangkat & Versi OS* | *Indikator saat koneksi terputus (Airplane Mode)* |

---

## 🎥 Video Demo

Berikut adalah demonstrasi aplikasi berdurasi 45 detik yang menunjukkan inisialisasi DI, pengecekan informasi perangkat, dan perubahan status jaringan:

👉 **[LINK VIDEO DEMO TUGAS MINGGU 8 - MARTINO KELVIN](https://drive.google.com/drive/folders/17uEZmh5SdTgb2Wu6DiX7H2Xtlsez7zQZ?usp=sharing)**

---

## 🛠️ Cara Menjalankan Aplikasi
1. Lakukan **Gradle Sync** untuk memastikan library Koin (`koin-core`, `koin-compose`) terunduh.
2. Pastikan file `AndroidManifest.xml` sudah memiliki atribut `android:name=".MyApp"` pada tag `<application>` agar Koin dapat berjalan.
3. Jalankan aplikasi pada perangkat Android.
4. Buka Menu **Profile** > **Settings** untuk melihat detail perangkat.

---
*© 2026 - Martino Kelvin - Pengembangan Aplikasi Mobile ITERA*