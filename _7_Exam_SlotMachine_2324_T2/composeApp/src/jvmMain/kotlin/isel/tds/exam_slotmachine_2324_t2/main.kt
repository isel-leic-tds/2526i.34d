package isel.tds.exam_slotmachine_2324_t2

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import isel.tds.exam_slotmachine_2324_t2.view.SlotMachineApp

//fun main() = application {
//    Window(
//        onCloseRequest = ::exitApplication,
//        title = "exam_slotmachine_2324_t2",
//    ) {
//        App()
//    }
//}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Slot Machine")
    { MaterialTheme { SlotMachineApp() } }
}