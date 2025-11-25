package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import isel.tds.tictactoecompose.model.Game
import isel.tds.tictactoecompose.model.Name
import isel.tds.tictactoecompose.storage.GameSerializer
import isel.tds.tictactoecompose.storage.TextFileStorage
import isel.tds.tictactoecompose.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun FrameWindowScope.TTTApp(onExit: () -> Unit) {

    MaterialTheme {
        //TODO: analyse full recomposition when player is swapped
        val vm = remember { AppViewModel(TextFileStorage<Name, Game>("savedGames", GameSerializer)) }
        MenuBar {
            Menu("Game") {
                Item("Start clash", onClick = vm::showStartDialog)
                Item("Join clash", onClick = vm::showJoinDialog)
                Item("New game", onClick = vm::newBoard)//{vm.newBoard()})
                Item("Show score", onClick = vm::toggleShowScore)
                Item("Exit", onClick = onExit)
            }
        }
        Column {

            if (vm.isRun) {
                BoardView(vm.game.board, vm::play)//{ pos -> vm.play(pos) })
                StatusBarView(vm.game.gameState)
            } else {
                Box(Modifier.size(GRID_SIZE, GRID_SIZE + STATUS_HEIGHT))
            }
            if (vm.showScoreDialog) {
                ScoreDialog(vm.game.score, vm::hideScore)
            }
            vm.startOrJoinType?.let {
                StartOrJoinDialog(it, vm::hideStartOrJoinDialog, vm::startOrJoinGame)
            }
        }
    }
}