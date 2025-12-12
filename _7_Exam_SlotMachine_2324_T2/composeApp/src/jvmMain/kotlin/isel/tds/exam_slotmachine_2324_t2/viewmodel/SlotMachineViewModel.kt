package isel.tds.exam_slotmachine_2324_t2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import isel.tds.exam_slotmachine_2324_t2.model.SlotState
import isel.tds.exam_slotmachine_2324_t2.model.isWinner

class SlotMachineViewModel {
    var playerName by mutableStateOf("")
    var slotState by mutableStateOf(SlotState.random())

    fun play(): Unit{
        slotState = SlotState.random()
    }

    fun isWinner() = slotState.isWinner()

    fun isPlayerNameValid() = playerName.isNotBlank() &&
            playerName.filter { c -> !c.isWhitespace() }.length>=3
}