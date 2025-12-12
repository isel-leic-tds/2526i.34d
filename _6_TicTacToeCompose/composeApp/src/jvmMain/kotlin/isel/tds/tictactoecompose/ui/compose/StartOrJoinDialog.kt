package isel.tds.tictactoecompose.ui.compose

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import isel.tds.tictactoecompose.model.Name

enum class StartOrJoinType(val txt: String) {
    StartDialog("Start"),
    JoinDialog("Join")
}

@Composable
fun StartOrJoinDialog(type: StartOrJoinType, close: () -> Unit, startOrJoinAction: (Name) -> Unit) {
    var name by remember { mutableStateOf("") }
    val isValid = Name.isValid(name)

    val fr = remember { FocusRequester() }
    fun keyHandler(ke: KeyEvent): Boolean {
        if (ke.key == Key.Enter && ke.type == KeyEventType.KeyDown) {
            if (isValid)
                startOrJoinAction(Name(name))
            return true
        }
        return false
    }
    AlertDialog(
        onDismissRequest = close,
        title = {
            Text(text = "Name to ${type.txt}", style = MaterialTheme.typography.h5)
        },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { newValue -> name = newValue },
                label = { Text("Name of game") },
                singleLine = true,
                modifier = Modifier.onKeyEvent(::keyHandler).focusRequester(fr)
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
    LaunchedEffect(Unit) {
        fr.requestFocus()
    }
}