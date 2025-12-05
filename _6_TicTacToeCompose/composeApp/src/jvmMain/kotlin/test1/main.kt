package test1

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*

fun log(text: String) = println("$text: ${Thread.currentThread().name}")

fun main1() {
//    println("Hello world!!")
    log("main")
    application(exitProcessOnExit = false) {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            log("application")
            Button(onClick = { log("onClick") }) { Text("Ok") }
        }
    }
    log("exit - outside application")
}

fun main2() {
    log("main")
    application(exitProcessOnExit = false) {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            log("application")
            var scope = rememberCoroutineScope()
            var clickable by remember { mutableStateOf(true) }
            var job: Job? by remember { mutableStateOf(null) }
            Row {
                Button(
                    enabled = clickable,
                    onClick = {
                        log("onClick1")
                        clickable = false
                        job = scope.launch {
                            log("onClick1 on coroutine")
                            repeat(5) { print('.'); delay(1000)/*Thread.sleep(1000)*/ }
                            job = null
                        }
                    }
                )
                { Text("Click me") }
                Button(
                    enabled = !clickable,
                    onClick = {
                        log("onClick2")
                        clickable = true
                        job?.cancel()
                    }
                )
                { Text("Enable click") }
            }

        }
    }
    log("exit - outside application")
}

fun main3() {
    log("main")
    application(exitProcessOnExit = false) {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            log("application")
            var scope = rememberCoroutineScope()
            var job: Job? by remember { mutableStateOf(null) }
            var clickable = job == null
            Row {
                Button(
                    enabled = clickable,
                    onClick = {
                        log("onClick1")
                        clickable = false
                        job = scope.launch {
                            log("onClick1 on coroutine")
                            var objRetornoPesado = withContext(Dispatchers.IO) {
                                log("onClick1 on coroutine after withContext")
                                repeat(5) { print('.'); delay(1000)/*Thread.sleep(1000)*/ }
                                job = null
                                "HeavyWorkResult"
                            }
                            log(objRetornoPesado)
                        }
                    }
                )
                { Text("Click me") }
                Button(
                    enabled = !clickable,
                    onClick = {
                        log("onClick2")
                        clickable = true
                        job?.cancel()
                    }
                )
                { Text("Enable click") }
            }

        }
    }
    log("exit - outside application")
}


fun main() {
    log("main")
    application(exitProcessOnExit = false) {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            log("application")
            var scope = rememberCoroutineScope()
            var job: Job? by remember { mutableStateOf(null) }
            var clickable = job == null
            Row {
                Button(
                    enabled = clickable,
                    onClick = {
                        log("onClick1")
                        clickable = false
                        job = scope.launch(Dispatchers.Default) {
                            log("onClick1 on coroutine")
                            var objRetornoPesado = withContext(Dispatchers.IO) {
                                log("onClick1 on coroutine after withContext")
                                repeat(5) { print('.'); delay(1000)/*Thread.sleep(1000)*/ }
                                job = null
                                "HeavyWorkResult"
                            }
//                            log(objRetornoPesado)
                            log("end of heavy processing")
                        }
                    }
                )
                { Text("Click me") }
                Button(
                    enabled = !clickable,
                    onClick = {
                        log("onClick2")
                        clickable = true
                        job?.cancel()
                    }
                )
                { Text("Enable click") }
            }

        }
    }
    log("exit - outside application")
}