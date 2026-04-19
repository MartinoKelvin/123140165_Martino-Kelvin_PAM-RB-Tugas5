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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.viewmodel.NewsViewModel
import org.example.project.viewmodel.NewsUiState
// ... import lainnya ...
import coil3.compose.AsyncImage
import org.example.project.data.Article

@Composable
fun NewsScreen(viewModel: NewsViewModel, onArticleClick: (Article) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF3E0)).padding(16.dp)) {
        Text("Tech News", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = uiState) {
            is NewsUiState.Success -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.articles) { article ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable { onArticleClick(article) }, // Navigasi ke detail
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column {
                                // Menampilkan Gambar Berita
                                AsyncImage(
                                    model = article.urlToImage,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxWidth().height(180.dp),
                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(article.title ?: "", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Text(article.description ?: "", style = MaterialTheme.typography.bodySmall, maxLines = 2)
                                }
                            }
                        }
                    }
                }
            }
            is NewsUiState.Loading -> { /* Indikator Loading [cite: 510] */ }
            is NewsUiState.Error -> { /* Tampilan Error [cite: 510] */ }
        }
    }
}

@Composable
fun ArticleCard(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray,
                maxLines = 3
            )
        }
    }
}
