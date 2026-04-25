# My News & Notes App - Pertemuan 7 (SQLDelight & DataStore)

Tugas ini mendemonstrasikan implementasi **Offline-First Architecture** menggunakan Kotlin Multiplatform (KMP), SQLDelight sebagai Database Lokal, dan Multiplatform Settings untuk penyimpanan preferensi.

## 👤 Identitas

- **Nama:** Martino Kelvin
- **NIM:** 123140165
- **Program Studi:** Informatika
- **Instansi:** Institut Teknologi Sumatera (ITERA)

---

## Link
https://drive.google.com/drive/folders/1rcBU-ZGkMuYIRVEQzlCblJ-1YXHfCntw?usp=sharing

---

## 🚀 Fitur Utama

1. **CRUD Operations (Offline-First):** Menambah, membaca, dan menghapus catatan langsung dari penyimpanan lokal.
2. **Search Functionality:** Fitur pencarian catatan berdasarkan judul atau isi menggunakan query SQL `LIKE`.
3. **Settings & Persistence:** Menyimpan preferensi tema (Dark/Light Mode) yang tetap bertahan meskipun aplikasi ditutup (Persistent).
4. **Reactive UI:** UI otomatis terupdate saat ada perubahan data di database menggunakan `StateFlow`.

---

## 🛠️ Tech Stack & Konfigurasi

- **SQLDelight:** Untuk Database SQLite Multiplatform.
- **Multiplatform Settings:** Untuk penyimpanan Key-Value (DataStore).
- **Kotlinx Datetime:** Manajemen waktu pembuatan catatan.
- **Material 3:** Desain UI modern dengan dukungan Dark Mode.

### Database Schema (`Note.sq`)

```sql
CREATE TABLE Note (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    created_at INTEGER NOT NULL
);

-- Query untuk Fitur Search
search:
SELECT * FROM Note
WHERE title LIKE ('%' || ? || '%')
OR content LIKE ('%' || ? || '%')
ORDER BY created_at DESC;
```

---

## 📂 Struktur Project (PBO Implementation)

Project ini menerapkan prinsip **Object-Oriented Programming (OOP)**, khususnya **Inheritance & Polimorfisme**, melalui mekanisme `expect/actual` pada Kotlin Multiplatform:

- **`DatabaseDriverFactory` (Abstraksi & Polimorfisme):** Menggunakan mekanisme `expect/actual` untuk mengabstraksi pembuatan driver database. Implementasi spesifik dilakukan pada masing-masing platform:
  - **Android:** Menggunakan `AndroidSqliteDriver` untuk akses SQLite sistem Android.
  - **iOS:** Menggunakan `NativeSqliteDriver` untuk akses SQLite sistem iOS.
- **`NoteRepository` (Encapsulation):** Bertindak sebagai _Single Source of Truth_ yang mengenkapsulasi logika akses data. Repository ini mengelola aliran data antara Database (SQLDelight) dan UI menggunakan `Flow`.
- **`ViewModel` (State Management):** Memisahkan logika bisnis dari UI (Separation of Concerns), memastikan state aplikasi tetap terjaga dan reaktif.

---

## 📺 Demo Video Checklist

Berikut adalah poin-poin yang didemonstrasikan dalam video untuk memenuhi kriteria penilaian:

- [X] **Fitur CRUD:** Menambah catatan baru dan menghapusnya dari daftar.
- [X] **Fitur Search:** Mencari catatan secara real-time menggunakan Search Bar (Query SQL `LIKE`).
- [X] **Settings & DataStore:** Mengubah tema aplikasi (Dark/Light Mode) dan memastikan pilihan tersimpan saat aplikasi dibuka kembali.
- [X] **Bukti Offline-First:** Mematikan koneksi internet (Airplane Mode) dan menunjukkan bahwa aplikasi tetap berfungsi penuh untuk membaca serta menambah data ke Database Lokal.
