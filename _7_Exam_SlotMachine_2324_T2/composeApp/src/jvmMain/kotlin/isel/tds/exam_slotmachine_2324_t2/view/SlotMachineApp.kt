package isel.tds.exam_slotmachine_2324_t2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import isel.tds.exam_slotmachine_2324_t2.model.SlotState
import isel.tds.exam_slotmachine_2324_t2.viewmodel.SlotMachineViewModel
import org.jetbrains.compose.resources.painterResource


@Composable
fun SlotMachineApp() {
    val slotMachineViewModel = remember { SlotMachineViewModel() }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayerInputDetails(
            nameGetter=slotMachineViewModel::playerName,
            nameSetter={name -> slotMachineViewModel.playerName=name}
            )
        SlotMachine(
            slotState=slotMachineViewModel::slotState,
            isEnabled=slotMachineViewModel::isPlayerNameValid,
            play=slotMachineViewModel::play
        )
        ResultPanel(slotMachineViewModel)
    }
}
@Composable
fun PlayerInputDetails(nameGetter: () -> String, nameSetter: (String) -> Unit) {
    OutlinedTextField(
        value = nameGetter(),
        onValueChange=nameSetter,
        label = { Text("Enter your name")},
        singleLine = true,
        modifier= Modifier.fillMaxWidth().padding(bottom = 16.dp)
    )
}
@Composable
fun SlotMachine(slotState: () -> SlotState, isEnabled: () -> Boolean, play: () -> Unit) {
    Row{
        val (slot1, slot2,slot3)=slotState()
        AddSlot(slot1)
        Spacer(modifier=Modifier.width(16.dp))
        AddSlot(slot2)
        Spacer(modifier=Modifier.width(16.dp))
        AddSlot(slot3)
    }
    Button(
        enabled = isEnabled(),
        onClick = play,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text("Play")
    }
}

@Composable
fun AddSlot(slot: Byte){
    Image(
        painter= painterResource(slot.toSlotImageResource()),
        contentDescription = null,
        modifier=Modifier.size(80.dp)
            .clip(RectangleShape)
            .background(Color.White)
            .padding(8.dp)
    )
}
@Composable
fun ResultPanel(slotMachineViewModel: SlotMachineViewModel) {
    val textToShow = if(slotMachineViewModel.playerName.isBlank())
        "Insert your name and press play!"
    else if (slotMachineViewModel.isWinner())
        "Congratulations, ${slotMachineViewModel.playerName}! You won!"
    else "Better luck next time, ${slotMachineViewModel.playerName}!"

    Text(
        text = textToShow,
        modifier = Modifier.padding(top = 16.dp)
    )
}