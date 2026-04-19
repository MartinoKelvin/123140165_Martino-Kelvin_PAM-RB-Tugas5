package org.example.project.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.data.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    article: Article,
    onBack: () -> Unit
) {
    // State untuk mengontrol scroll pada kolom konten
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Berita",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF57C00), // Warna khas orange ITERA/Notes kamu
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFFF3E0)) // Background krem orange agar soft
                .verticalScroll(scrollState) // Mengaktifkan scroll untuk teks panjang
                .padding(16.dp)
        ) {
            // Gambar Utama Artikel (Coil3 AsyncImage)
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "Gambar Berita",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Judul Berita (Tampil penuh tanpa limit kata)
            Text(
                text = article.title ?: "Judul Tidak Tersedia",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Info Publikasi
            Text(
                text = "Diterbitkan pada: ${article.publishedAt ?: "Waktu tidak diketahui"}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )

            // Konten Berita (Teks penuh, scrollable)
            // Menggabungkan content dan description jika salah satu kosong
            val fullContent = article.content ?: article.description ?: "Maaf, detail konten untuk berita ini tidak tersedia."

            Text(
                text = fullContent,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.3,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Spacer tambahan di bawah agar teks terakhir tidak terlalu mepet dengan edge layar
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}