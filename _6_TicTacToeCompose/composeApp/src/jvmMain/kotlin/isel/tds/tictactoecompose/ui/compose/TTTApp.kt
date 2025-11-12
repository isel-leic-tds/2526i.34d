package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import isel.tds.tictactoecompose.model.Player
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun TTTApp() {
    MaterialTheme {
        //TODO: analyse full recomposition when player is swapped
        var player by remember { mutableStateOf(Player.X) }
        Column {
            Button(onClick = {
                println("clicker other player $player");
                player = player.other()
            }
            ) {
                Text("Other player")
            }
            PlayerView(player)
        }
    }
}