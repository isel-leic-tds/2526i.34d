package isel.tds.democompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.FrameWindowScope
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun FrameWindowScope.TDSApp() {
    println("inside TDSApp")
    MaterialTheme {
        println("inside MaterialTheme")
        var text by remember { println("inside remember init"); mutableStateOf("HelloWorld"); }
        val w = this.window
        Column() {
            println("inside Column")
            var buttonEnabled by remember { mutableStateOf(true) }

            Button(onClick = { text = "HelloDesktop!"; buttonEnabled = true }) {
                println("inside Button 1");
                Text(text)
            }

            var text2 by remember { println("inside remember init2"); mutableStateOf("HelloWorld2"); }
            Button(
                enabled = buttonEnabled,
                onClick = {
                    w.title = "bla bla";
                    buttonEnabled = false
                    text2 = "bla bla"
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                println("inside Button 2");
                Text(text2)
            }
        }
    }
}