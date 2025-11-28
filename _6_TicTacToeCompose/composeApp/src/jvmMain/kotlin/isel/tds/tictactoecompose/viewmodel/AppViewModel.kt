package isel.tds.tictactoecompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import isel.tds.tictactoecompose.model.*
import isel.tds.tictactoecompose.ui.compose.StartOrJoinType

class AppViewModel(storage: GameStorage) {
    var clash by mutableStateOf(Clash(storage))
    var showScoreDialog by mutableStateOf(false)
    var startOrJoinType: StartOrJoinType? by mutableStateOf(null)
        private set

    val run get() = clash as ClashRun
    val game: Game get() = run.game

    val isRun: Boolean get() = clash is ClashRun

    fun newBoard() {
        //game = game.restartGame()
        clash = clash.new()
    }

    fun startOrJoinGame(name: Name) {
        if (startOrJoinType == StartOrJoinType.StartDialog)
            clash = clash.start(name)
        else clash = clash.join(name)

        hideStartOrJoinDialog()
    }


    fun toggleShowScore() {
        showScoreDialog = !showScoreDialog
    }

    fun play(pos: Position): Unit {
        clash = clash.play(pos)
    }

    fun hideScore() {
        showScoreDialog = false
    }

    fun showStartDialog() {
        startOrJoinType = StartOrJoinType.StartDialog
    }

    fun showJoinDialog() {
        startOrJoinType = StartOrJoinType.JoinDialog
    }

    fun hideStartOrJoinDialog() {
        startOrJoinType = null
    }

    fun refresh() {
        clash = clash.refresh()
    }

    //    fun canRefresh() = true
    val canRefresh: Boolean
        get() = isRun && ((game.gameState is Run &&
                run.sidePlayer != (game.gameState as Run).turn)
                || game.gameState is Win || game.gameState is Draw)


    fun exit() {
        clash.deleteIfIsOwner()
    }
}