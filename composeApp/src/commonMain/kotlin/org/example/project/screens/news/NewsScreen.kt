package org.example.project.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.data.Article
import org.example.project.viewmodel.NewsViewModel
import org.example.project.viewmodel.NewsUiState

@Composable
fun NewsScreen(viewModel: NewsViewModel, onArticleClick: (Article) -> Unit) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tech News",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFF57C00)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- HANDLER UI STATES (Rubrik: UI States 25%) ---
        when (val state = uiState) {

            // 1. STATE LOADING
            is NewsUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(color = Color(0xFFF57C00))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Mengambil berita...", color = Color.Gray)
                    }
                }
            }

            // 2. STATE SUCCESS
            is NewsUiState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(state.articles) { article ->
                        NewsCard(
                            article = article,
                            onClick = { onArticleClick(article) }
                        )
                    }
                }
            }

            // 3. STATE ERROR
            is NewsUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Text(
                            text = "Gagal memuat berita:\n${state.message}",
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.fetchNews() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00))
                        ) {
                            Text("Coba Lagi")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NewsCard(article: Article, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Gambar Berita menggunakan Coil3
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = article.title ?: "No Title",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = article.description ?: "Tidak ada deskripsi tersedia.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 3
                )
            }
        }
    }
}