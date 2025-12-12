package isel.tds.tictactoecompose.ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import isel.tds.tictactoecompose.model.Player
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import tictactoecompose.composeapp.generated.resources.Res
import tictactoecompose.composeapp.generated.resources.circle
import tictactoecompose.composeapp.generated.resources.cross


@Composable
@Preview
fun PlayerView(
    player: Player?,
    modifier: Modifier = Modifier.size(CELL_SIZE),
    onClick: () -> Unit = {}
) {
    if (player == null)
        Box(modifier.clickable(onClick = onClick))
    else {
        val resource = when (player) {
            Player.O -> Res.drawable.circle
            Player.X -> Res.drawable.cross
        }
        var zoom by remember { mutableStateOf(0.1f) }
        Image(
            painter = painterResource(resource),
            contentDescription = null,
            modifier = modifier.graphicsLayer(scaleX = zoom, scaleY = zoom)
        )
        LaunchedEffect(player) {
            while (zoom < 1f) {
                delay(50)
                zoom += 0.1f
            }
        }
    }
}

@Composable
@Preview
fun testPlayerView() {
    Column {
        PlayerView(null)
        PlayerView(Player.X)
        PlayerView(Player.O)
    }
}