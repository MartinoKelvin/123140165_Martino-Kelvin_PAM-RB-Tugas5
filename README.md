# 📰 News & Notes App - Kotlin Multiplatform (KMP)

Aplikasi ini dikembangkan sebagai tugas praktikum **Pengembangan Aplikasi Mobile (PAM)** di **Informatika ITERA**. Project ini mendemonstrasikan implementasi Networking (Ktor), Image Loading (Coil), dan Arsitektur MVVM pada Kotlin Multiplatform.

## 👤 Identitas Mahasiswa
- **Nama**: Martino Kelvin
- **NIM**: 123140165
- **Program Studi**: Informatika
- **Instansi**: Institut Teknologi Sumatera (ITERA)

## 🎥 Link Video Demo
- **Video Link**: https://drive.google.com/drive/folders/13Jz_8uMY6uVJFZ_Vqv9dCKzhHlVvx8oX

## 🚀 Fitur Utama
- **News Reader (Real-time)**: Mengambil berita teknologi terbaru dari API `saurav.tech` menggunakan Ktor Client.
- **News Detail**: Menampilkan konten berita secara penuh dengan dukungan gambar (Coil3) dan fitur scrollable.
- **Notes Management**: Fitur CRUD catatan dengan integrasi favorit.
- **Modern UI**: Dibangun sepenuhnya menggunakan Jetpack Compose dengan skema warna konsisten (Orange-Cream).

## 📊 Manajemen UI States (Rubrik Penilaian: 25%)
Aplikasi menggunakan `sealed class NewsUiState` untuk menangani siklus hidup pengambilan data secara reaktif:
1.  **Loading State**: Menampilkan `CircularProgressIndicator` saat aplikasi melakukan request ke API. Memberikan feedback visual kepada pengguna agar tidak bingung saat proses sinkronisasi data berlangsung.
2.  **Success State**: Menampilkan daftar berita dalam bentuk `LazyColumn` setelah data berhasil diparsing dari JSON.
3.  **Error State**: Menampilkan pesan error yang informatif dan tombol **"Retry"** jika terjadi gangguan koneksi (Network Error) atau kegagalan server, sehingga pengguna bisa mencoba memuat ulang data tanpa restart aplikasi.

## 🛠️ Stack Teknologi
- **Core**: Kotlin Multiplatform (KMP)
- **UI Framework**: Compose Multiplatform
- **Networking**: Ktor Client (Content Negotiation & JSON Serialization)
- **Image Loading**: Coil 3 (Network Support)
- **Navigation**: Jetpack Navigation Compose
- **Concurrency**: Kotlin Coroutines & Flow

## 📂 Struktur Proyek
- `data/`: Model data (`NewsResponse`, `Article`) dan `NewsRepository`.
- `viewmodel/`: `NewsViewModel` (StateFlow & mutableStateOf untuk Detail).
- `screens/news/`: `NewsScreen` (List) dan `NewsDetailScreen` (Detail Content).
- `navigation/`: Konfigurasi rute navigasi dan Bottom Navigation.

## 📖 Cara Menjalankan Project
1. **Sync Gradle**: Pastikan `mavenCentral()` sudah terdaftar di `settings.gradle.kts`.
2. **Permission**: Izin internet sudah terpasang di `AndroidManifest.xml`.
3. **Run**: Pilih modul `:composeApp` dan jalankan pada perangkat Android.

---
© 2026 Martino Kelvin - 123140165 - Informatika ITERA