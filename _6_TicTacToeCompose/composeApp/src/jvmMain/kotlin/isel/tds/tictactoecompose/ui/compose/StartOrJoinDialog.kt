package isel.tds.tictactoecompose.ui.compose

import androidx.compose.material.*
import androidx.compose.runtime.*
import isel.tds.tictactoecompose.model.Name

enum class StartOrJoinType(val txt: String) {
    StartDialog("Start"),
    JoinDialog("Join")
}

@Composable
fun StartOrJoinDialog(type: StartOrJoinType, close: () -> Unit, startOrJoinAction: (Name) -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = close,
        title = {
            Text(text = "Name to ${type.txt}", style = MaterialTheme.typography.h5)
        },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { newValue -> name = newValue },
                label = { Text("Name of game") }
            )
        },
        confirmButton = {
            TextButton(
                enabled = Name.isValid(name),
                onClick = { startOrJoinAction(Name(name)) }) { Text(type.txt) }
        },
        dismissButton = {
            TextButton(onClick = close) { Text("Cancel") }
        }
    )
}