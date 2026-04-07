package org.example.project.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import org.example.project.components.BottomNavBar
import org.example.project.screens.favorites.FavoritesScreen
import org.example.project.screens.profile.ProfileScreen
import org.example.project.screens.notes.*


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

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

            // Bottom Tabs
            composable(Screen.Notes.route) {
                NoteListScreen(
                    onNoteClick = { noteId ->
                        navController.navigate(Screen.NoteDetail.createRoute(noteId))
                    }
                )
            }

            composable(Screen.Favorites.route) {
                FavoritesScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            // Add Note
            composable(Screen.AddNote.route) {
                AddNoteScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            // Note Detail (with argument)
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

            // Edit Note (with argument)
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