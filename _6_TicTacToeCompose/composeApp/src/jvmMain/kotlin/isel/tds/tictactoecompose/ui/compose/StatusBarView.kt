package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import isel.tds.tictactoecompose.model.*

val STATUS_HEIGHT = 50.dp

@Composable
private fun LabeledCell(txt: String, player: Player?) {
    Text(txt)
//    player?.let {PlayerView(player = player, modifier = Modifier.size(50.dp)) }
    if (player != null)
        PlayerView(player = player, modifier = Modifier.size(50.dp))
}

@Composable
fun StatusBarView(gameState: GameState) = Row(
    modifier = Modifier.background(Color.LightGray)
        .width(GRID_SIZE)
        .height(STATUS_HEIGHT),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    val (txt, player) = when (gameState) {
        is Run -> "Turn:" to gameState.turn
        is Win -> "Winner:" to gameState.winner
        is Draw -> "Draw" to null
    }
    LabeledCell(txt, player)
}