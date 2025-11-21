package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import isel.tds.tictactoecompose.model.Player
import isel.tds.tictactoecompose.model.Score

@Composable
fun ScoreDialog(score: Score, onClose: () -> Unit) = AlertDialog(
    onDismissRequest = onClose,
    title = { Text("Score") },
    text = { ScoreDialogContent(score) },
    confirmButton = { TextButton(onClick = onClose) { Text("Close") } }
)

@Composable
fun ScoreDialogContent(score: Score) {
//    Text("Score Content")
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
        Column {
            Player.entries.forEach { player ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PlayerView(
                        modifier = Modifier.size(20.dp),
                        player = player
                    )
                    Text(text = " - ${score[player]}")
                }
            }
        }
        Text(text = "Draws - ${score[null]}")
    }
}