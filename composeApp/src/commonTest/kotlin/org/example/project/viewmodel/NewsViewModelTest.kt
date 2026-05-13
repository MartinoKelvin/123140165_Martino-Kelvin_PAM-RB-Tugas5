package org.example.project.viewmodel

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.project.data.Article
import org.example.project.data.NewsRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class NewsViewModelTest {

    private val mockRepo = mockk<NewsRepository>(relaxed = true)
    private val testArticle = Article(
        title = "Tech News",
        description = "Content",
        url = "https://itera.ac.id",
        urlToImage = ""
    )

    @Test
    fun `initial state emits loading then success`() = runTest {

        coEvery { mockRepo.getNews() } returns Result.success(listOf(testArticle))


        val viewModel = NewsViewModel(mockRepo)

        viewModel.uiState.test {
            assertIs<NewsUiState.Loading>(awaitItem())
            val successState = awaitItem()
            assertIs<NewsUiState.Success>(successState)
            assertEquals(1, successState.articles.size)
            cancelAndIgnoreRemainingEvents() // [cite: 327]
        }
    }

    @Test
    fun `fetch news emits error state on failure`() = runTest {

        coEvery { mockRepo.getNews() } returns Result.failure(Exception("Network Error"))

        val viewModel = NewsViewModel(mockRepo)


        viewModel.uiState.test {
            skipItems(1)
            val errorState = awaitItem()
            assertIs<NewsUiState.Error>(errorState)
            assertEquals("Network Error", (errorState as NewsUiState.Error).message)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `selectArticle updates selectedArticle state`() {
        val viewModel = NewsViewModel(mockRepo)

        // Act
        viewModel.selectArticle(testArticle)

        // Assert [cite: 221]
        assertEquals(testArticle, viewModel.selectedArticle)
    }

    @Test
    fun `fetchNews triggers repository call`() = runTest {
        // Arrange [cite: 240]
        coEvery { mockRepo.getNews() } returns Result.success(emptyList())
        val viewModel = NewsViewModel(mockRepo)

        // Act
        viewModel.fetchNews()

        // Assert: Verifikasi interaksi dengan repository [cite: 243, 334]
        coVerify(atLeast = 1) { mockRepo.getNews() }
    }
}