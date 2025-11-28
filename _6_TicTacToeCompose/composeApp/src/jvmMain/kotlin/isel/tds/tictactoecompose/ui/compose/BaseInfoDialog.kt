package isel.tds.tictactoecompose.ui.compose

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun BaseInfoDialog(title: String, closeAction: () -> Unit, content: @Composable () -> Unit) = AlertDialog(
    onDismissRequest = closeAction,
    title = { Text(title) },
    text = content,
    confirmButton = { TextButton(closeAction) { Text("Close") } }
)