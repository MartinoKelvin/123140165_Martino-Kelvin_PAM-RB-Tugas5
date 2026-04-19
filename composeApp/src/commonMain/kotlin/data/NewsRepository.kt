package org.example.project.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class NewsRepository(private val client: HttpClient) {
    suspend fun getNews(): Result<List<Article>> {
        return try {

            val response: NewsResponse = client.get("https://saurav.tech/NewsAPI/top-headlines/category/technology/in.json").body()
            Result.success(response.articles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}