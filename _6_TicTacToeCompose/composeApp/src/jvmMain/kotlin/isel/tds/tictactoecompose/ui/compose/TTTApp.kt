package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import isel.tds.tictactoecompose.model.Game
import isel.tds.tictactoecompose.model.play
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun FrameWindowScope.TTTApp(onExit: () -> Unit) {

    MaterialTheme {
        //TODO: analyse full recomposition when player is swapped

        var game by remember { mutableStateOf(Game()) }
//        var player by remember { mutableStateOf(Player.X) }
        MenuBar {
            Menu("Game") {
                Item("Exit", onClick = onExit)
            }
        }
        Column {

            BoardView(game.board, { pos -> game = game.play(pos) })
            StatusBarView(game.gameState)
        }
    }
}