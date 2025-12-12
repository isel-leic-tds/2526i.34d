package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaitingIndicator() = CircularProgressIndicator(
    Modifier.size(GRID_SIZE).padding(30.dp),
    strokeWidth = 15.dp
)