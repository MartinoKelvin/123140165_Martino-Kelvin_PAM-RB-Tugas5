import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, newspaper: ImageVector, string: String) {

    object News : Screen ("news", Icons.Default.Newspaper, "News")
    object Notes : Screen("notes", Icons.Default.Newspaper, "News")
    object Favorites : Screen("favorites", Icons.Default.Newspaper, "News")
    object Profile : Screen("profile", Icons.Default.Newspaper, "News")

    object NoteDetail : Screen("note_detail/{noteId}", Icons.Default.Newspaper, "News") {
        fun createRoute(noteId: Int) = "note_detail/$noteId"
    }

    object AddNote : Screen("add_note", Icons.Default.Newspaper, "News")

    object EditNote : Screen("edit_note/{noteId}", Icons.Default.Newspaper, "News") {
        fun createRoute(noteId: Int) = "edit_note/$noteId"
    }
}