package isel.tds.tictactoecompose.ui.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun ErrorDialog(errorMsg: String, closeAction: () -> Unit) = BaseInfoDialog(
    title = "Error",
    closeAction = closeAction
) { Text(errorMsg, style = MaterialTheme.typography.h6) }

