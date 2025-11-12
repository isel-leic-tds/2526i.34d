package isel.tds.tictactoecompose.ui.compose

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "tictactoecompose",
    ) {
        TTTApp()
    }
}