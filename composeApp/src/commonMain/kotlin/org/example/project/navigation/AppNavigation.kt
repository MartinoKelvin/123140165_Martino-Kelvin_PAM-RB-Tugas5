package org.example.project.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import io.ktor.client.*
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.project.components.BottomNavBar
import org.example.project.screens.favorites.FavoritesScreen
import org.example.project.screens.profile.ProfileScreen
import org.example.project.screens.profile.SettingsScreen // Pastikan diimport
import org.example.project.screens.notes.*
import org.example.project.screens.news.NewsScreen
import org.example.project.screens.news.NewsDetailScreen
import org.example.project.viewmodel.NoteViewModel
import org.example.project.viewmodel.NewsViewModel
import org.example.project.data.NewsRepository
import org.example.project.viewmodel.SettingsViewModel

@Composable
fun AppNavigation(
    noteViewModel: NoteViewModel,
    settingsViewModel: SettingsViewModel
) {
    val navController = rememberNavController()

    // Setup HttpClient dengan Ktor
    val client = remember {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
            }
        }
    }

    val newsRepository = remember { NewsRepository(client) }

    // Inisialisasi ViewModel News
    val newsViewModel: NewsViewModel = viewModel {
        NewsViewModel(newsRepository)
    }

    // PENTING: Jangan inisialisasi noteViewModel lagi di sini karena sudah dikirim dari MainActivity

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddNote.route) }
            ) {
                Text("+")
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Notes.route,
            modifier = Modifier.padding(padding)
        ) {

            // --- TAB 1: News ---
            composable(Screen.News.route) {
                NewsScreen(
                    viewModel = newsViewModel,
                    onArticleClick = { article ->
                        newsViewModel.selectArticle(article)
                        navController.navigate("news_detail")
                    }
                )
            }

            // --- TAB 2: Notes ---
            composable(Screen.Notes.route) {
                NoteListScreen(
                    viewModel = noteViewModel,
                    onNoteClick = { noteId ->
                        navController.navigate(Screen.NoteDetail.createRoute(noteId))
                    }
                )
            }

            // --- TAB 3: Favorites ---
            composable(Screen.Favorites.route) {
                FavoritesScreen(viewModel = noteViewModel)
            }

            // --- TAB 4: Profile ---
            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            // --- TAB 5: Settings (Poin Tugas DataStore) ---
            composable("settings_screen") {
                SettingsScreen(
                    viewModel = settingsViewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            // --- Screen Detail Berita (Non-Tab) ---
            composable("news_detail") {
                val article = newsViewModel.selectedArticle
                if (article != null) {
                    NewsDetailScreen(
                        article = article,
                        onBack = { navController.popBackStack() }
                    )
                }
            }

            // --- Screens Catatan Lainnya ---
            composable(Screen.AddNote.route) {
                AddNoteScreen(
                    viewModel = noteViewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = Screen.NoteDetail.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                NoteDetailScreen(
                    noteId = noteId,
                    onBack = { navController.popBackStack() },
                    onEdit = {
                        navController.navigate(Screen.EditNote.createRoute(noteId))
                    }
                )
            }

            composable(
                route = Screen.EditNote.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                EditNoteScreen(
                    noteId = noteId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}