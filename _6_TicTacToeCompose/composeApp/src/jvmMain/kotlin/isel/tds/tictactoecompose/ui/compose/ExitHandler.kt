package isel.tds.tictactoecompose.ui.compose

object ExitHandler {

    var exitHandlers: List<() -> Unit> = emptyList()
        private set

    var exitApplication: (() -> Unit)? = null
        private set

    fun runExitApplication() {
        exitHandlers.forEach { exitHandler -> exitHandler() }
        exitApplication?.invoke()
    }

    fun registerExitHandler(exitHandlerToAdd: () -> Unit) {
        exitHandlers = exitHandlers + exitHandlerToAdd
    }

    fun registerExitApplication(exitApplicationToAdd: () -> Unit) {
        exitApplication = exitApplicationToAdd
    }
}