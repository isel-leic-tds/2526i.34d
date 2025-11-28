package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import isel.tds.tictactoecompose.model.Player
import isel.tds.tictactoecompose.model.Score


@Composable
fun ScoreDialog(score: Score, closeAction: () -> Unit) = BaseInfoDialog(
    title = "Score",
    closeAction = closeAction,
    content = { ScoreDialogContent(score) }
)

@Composable
fun ScoreDialogContent(score: Score) {
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