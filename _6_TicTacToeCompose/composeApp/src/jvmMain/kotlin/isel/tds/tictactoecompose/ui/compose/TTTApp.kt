package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
fun FrameWindowScope.TTTApp() {

    MaterialTheme {
        //TODO: analyse full recomposition when player is swapped
        var scope = rememberCoroutineScope()
        val vm = remember {
            val st = TextFileStorage<Name, Game>("savedGames", GameSerializer)
            //val driver: MongoDriver = MongoDriver("JogoGalo34D")
            //val st = MongoStorage<Name, Game>("savedGames", driver, GameSerializer)
            val myVm = AppViewModel(st, scope);
            ExitHandler.registerExitHandler(myVm::cleanup);
            myVm
        }
        MenuBar {
            Menu("Game") {
                Item("Start clash", onClick = vm::showStartDialog)
                Item("Join clash", onClick = vm::showJoinDialog)
                Item("Refresh", enabled = vm.canRefresh, onClick = vm::refresh)
                // {scope.launch{vm.refresh()}}) // use withContext(Dispatchers.IO) to change to IO threads
                // {scope.launch(Dispatchers.IO){vm.refresh()}})
                Item("New game", onClick = vm::newBoard)//{vm.newBoard()})
                Item("Show score", enabled = vm.isClashRun, onClick = vm::toggleShowScore)
                Item("Exit", onClick = ExitHandler::runExitApplication)
            }
        }
        Column {

            if (vm.isClashRun) {
                BoardView(vm.game.board, vm::play)//{ pos -> vm.play(pos) })
                StatusBarView(vm.game.gameState, vm.clashRun.sidePlayer, vm.clashRun.name)
            } else {
                Box(Modifier.size(GRID_SIZE, GRID_SIZE + STATUS_HEIGHT))
            }
            if (vm.showScoreDialog) {
                ScoreDialog(vm.game.score, vm::hideScore)
            }
            vm.startOrJoinType?.let {
                StartOrJoinDialog(it, vm::hideStartOrJoinDialog, vm::startOrJoinGame)
            }
            vm.errorMessage?.let { msg -> ErrorDialog(msg, vm::hideError) }
        }
        if (vm.isWaiting) WaitingIndicator()
    }
}