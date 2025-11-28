package isel.tds.tictactoecompose.ui.compose

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import tictactoecompose.composeapp.generated.resources.Res
import tictactoecompose.composeapp.generated.resources.cross


fun main() = application {
    ExitHandler.registerExitApplication(::exitApplication)

    Window(
        state = WindowState(size = DpSize.Unspecified),
        onCloseRequest = ExitHandler::runExitApplication,
        // resizable = false,
        title = "tictactoecompose",
        icon = painterResource(Res.drawable.cross)
    ) {
        TTTApp()
    }
}