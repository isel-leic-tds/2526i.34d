package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import isel.tds.tictactoecompose.model.Game
import isel.tds.tictactoecompose.model.play
import isel.tds.tictactoecompose.model.restartGame
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun FrameWindowScope.TTTApp(onExit: () -> Unit) {

    MaterialTheme {
        //TODO: analyse full recomposition when player is swapped

        var game by remember { mutableStateOf(Game()) }
        var showScoreDialog by remember { mutableStateOf(false) }
        MenuBar {
            Menu("Game") {
                Item("New game", onClick = { game = game.restartGame() })
                Item("Show score", onClick = { showScoreDialog = !showScoreDialog })
                Item("Exit", onClick = onExit)
            }
        }
        Column {

            BoardView(game.board, { pos -> game = game.play(pos) })
            StatusBarView(game.gameState)

            if (showScoreDialog) {
                ScoreDialog(game.score, { showScoreDialog = false })
            }
        }
    }
}