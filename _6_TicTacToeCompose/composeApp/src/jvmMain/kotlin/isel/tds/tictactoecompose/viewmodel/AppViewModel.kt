package isel.tds.tictactoecompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import isel.tds.tictactoecompose.model.*
import isel.tds.tictactoecompose.ui.compose.StartOrJoinType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test1.log

class AppViewModel(storage: GameStorage, val scope: CoroutineScope) {
    var clash by mutableStateOf(Clash(storage))
    var showScoreDialog by mutableStateOf(false)
        private set
    var startOrJoinType: StartOrJoinType? by mutableStateOf(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isWaiting: Boolean by mutableStateOf(false)
        private set

    val clashRun get() = clash as ClashRun
    val game: Game get() = clashRun.game

    val isClashRun: Boolean get() = clash is ClashRun

    fun newBoard() = exec(Clash::new)

    fun startOrJoinGame(name: Name) {
        if (startOrJoinType == StartOrJoinType.StartDialog)
            clash = clash.start(name)
        else clash = clash.join(name)

        hideStartOrJoinDialog()
    }


    fun toggleShowScore() {
        showScoreDialog = !showScoreDialog
    }

    private fun exec(action: Clash.() -> Clash) {
        try {
            clash = clash.action()
        } catch (e: TTTFatalException) {
            cleanup()
            clash = Clash(clash.st)
            errorMessage = e.message
        } catch (e: Exception) {
            errorMessage = e.message
        }
    }

    fun play(pos: Position): Unit {
        exec { isWaiting = true; play(pos) }
        waitForOtherSide()
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

//    fun refresh() = exec() {
//        log("refresh")
//        var auxClash: Clash? = null
//        var job = scope.launch {
//            log("refresh in coroutine")
//            auxClash = clash.refresh()
//        }
//
//        checkNotNull(auxClash)
//        auxClash
//    }

    fun refresh() {
        scope.launch {
            try {
                log("refresh in coroutine")
                clash = clash.refresh()
                isWaiting = false;
            } catch (e: TTTFatalException) {
                cleanup()
                clash = Clash(clash.st)
                errorMessage = e.message
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    //    fun canRefresh() = true
    val canRefresh: Boolean
        get() = isClashRun && ((game.gameState is Run &&
                clashRun.sidePlayer != (game.gameState as Run).turn)
                || game.gameState is Win || game.gameState is Draw)


    fun cleanup() = exec(Clash::deleteIfIsOwner)

    fun hideError() {
        errorMessage = null
    }

    fun waitForOtherSide() {
        scope.launch {
            do {
                delay(3000)
                try {
                    clash = clash.refresh()
                    isWaiting = false
                } catch (e: NoChangesException) {
                } catch (e: Exception) {
                    errorMessage = e.message
                }
            } while (isWaiting)
        }
    }
}