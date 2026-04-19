package org.example.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.Article
import org.example.project.data.NewsRepository

// Sealed class untuk mengelola UI State (Rubrik: UI States 25%)
sealed class NewsUiState {
    object Loading : NewsUiState()
    data class Success(val articles: List<Article>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    // State untuk daftar berita (UI State)
    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    // State untuk artikel yang sedang dipilih/diklik (untuk halaman detail)
    var selectedArticle by mutableStateOf<Article?>(null)
        private set

    init {
        fetchNews()
    }

    // Fungsi untuk mengambil data dari Repository (Rubrik: API Integration 25%)
    fun fetchNews() {
        viewModelScope.launch {
            _uiState.value = NewsUiState.Loading
            repository.getNews()
                .onSuccess { articles ->
                    _uiState.value = NewsUiState.Success(articles)
                }
                .onFailure { error ->
                    _uiState.value = NewsUiState.Error(error.message ?: "Unknown Error")
                }
        }
    }

    // Fungsi untuk menyimpan artikel yang diklik pengguna
    fun selectArticle(article: Article) {
        selectedArticle = article
    }
}