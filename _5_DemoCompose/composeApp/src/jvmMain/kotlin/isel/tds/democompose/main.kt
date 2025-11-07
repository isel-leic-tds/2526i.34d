package isel.tds.democompose

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    println("inside application");
    Window(
        onCloseRequest = ::exitApplication,
        title = "democompose",
    ) {
        println("inside Window");

//        App()
        TDSApp()
    }
}