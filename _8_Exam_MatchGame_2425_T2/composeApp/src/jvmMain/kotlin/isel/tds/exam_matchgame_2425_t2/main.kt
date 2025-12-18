package isel.tds.exam_matchgame_2425_t2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.onClick
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

//fun main2() = application {
//    Window(
//        onCloseRequest = ::exitApplication,
//        title = "exam_matchgame_2425_t2",
//    ) {
//        App()
//    }
//}

fun main() = application {
    Window(::exitApplication, title = "TDS - Match Game") {
        MatchApp()
    }
}

@Composable
fun MatchApp() {
    val vm = remember {
        MatchViewModel(numOfPairs = 4)
    }
    Column() {
        CardGrid(vm.rows, vm.cols, vm::card, vm::flipCard)
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
            Text("Fails: ${vm.game.fails}", fontSize = 32.sp)
            Button(vm::hide, enabled = vm.game.is2Turned()) {
                Text("Hide")
            }
            Button(vm::newGame, enabled = vm.game.isOver()) {
                Text("New Game")
            }
        }
    }
}

enum class Face { DOWN, UP, MATCHED }
data class Card(val pair: Int, val state: Face)
data class MatchGame(val cards: List<Card>, val fails: Int = 0)

fun MatchGame(numOfPairs: Int) =
    MatchGame(
        List(numOfPairs) {
            Card(it, Face.DOWN)
        }.let { it + it }.shuffled()
    )

fun MatchGame.isOver() = cards.all { it.state == Face.MATCHED }
fun MatchGame.is2Turned() = cards.count { it.state == Face.UP } == 2


//1. Implemente a função MatchGame.hideTurned():MatchGame do modelo,
// para retornar um jogo em que os dois cartões virados (Face.UP) ficam novamente escondidos (Face.DOWN).
// A função deve lançar uma exceção IllegalState se não existirem exatamente dois cartões virados.
fun MatchGame.hideTurned(): MatchGame {
    check(cards.filter { card -> card.state == Face.UP }
        .count() == 2) { "2 cards must be faced up to call hide turned" }
    return MatchGame(cards.map { card -> if (card.state == Face.UP) card.copy(state = Face.DOWN) else card })
    //instead of copy: Card(card.pair, Face.DOWN)
}

//2. Implemente a função MatchGame.flipCard(index: Int):MatchGame do modelo,
// para retornar um jogo em que o cartão do índice index fica virado (Face.UP).
// Caso já exista um cartão virado e seja do mesmo par, o jogo retornado fica com esses dois cartões no estado (Face.MATCHED).
// A função deve lançar uma exceção IllegaArgument se o index não for válido.
// Deve lançar IllegalState se a carta a virar não está escondida (Face.DOWN) ou se o número de cartas já viradas for 2 ou superior.
fun MatchGame.flipCard(index: Int): MatchGame {
    require(index in 0..<cards.size) { "Index not valid" }
    check(cards[index].state == Face.DOWN) { "Card must be faced down to be turned up" }
    check(cards.filter { card -> card.state == Face.UP }.count() < 2) { "Can't have 2 or more cards turned up" }

    val firstCardUp: Card? = cards.filter { card -> card.state == Face.UP }.firstOrNull()

    val newCards: List<Card> =
        if (firstCardUp != null && firstCardUp.pair == cards[index].pair) {
            //Change state of both cards to matched
            cards.map { card -> if (card.pair == firstCardUp.pair) card.copy(state = Face.MATCHED) else card }
        } else {
            //Just change the flipped card
            cards.mapIndexed { testingIndex, card -> if (testingIndex == index) card.copy(state = Face.UP) else card }
        }
    return this.copy(cards = newCards)
}


class MatchViewModel(val numOfPairs: Int) {
    init {
        require(numOfPairs >= 2)
    }

    var game: MatchGame by mutableStateOf(MatchGame(numOfPairs))
        private set

    val cols: Int get() = 4

    val rows: Int get() = (numOfPairs * 2) / cols


    fun newGame() {
        game = MatchGame(numOfPairs)
    }

    fun hide() {
        game = game.hideTurned()
    }

    fun convertToIdx(row: Int, col: Int) = (row * cols) + col
    fun card(row: Int, col: Int) = game.cards[convertToIdx(row, col)]


    fun flipCard(row: Int, col: Int) {
        game = game.flipCard(convertToIdx(row, col))
    }
}

@Composable
fun CardGrid(rows: Int, cols: Int, cardGetter: (row: Int, col: Int) -> Card, onClick: (row: Int, col: Int) -> Unit) {
    repeat(rows) { row ->
        Row {
            repeat(cols) { col ->
//                CardView(cardGetter(row, col), { throw Exception("mmm"); onClick(row, col) })
                CardView(cardGetter(row, col), { onClick(row, col) })
            }
        }
    }
}

@Composable
fun CardView(card: Card, onClick: () -> Unit) {
    val bgColor: Color =
        if (card.state == Face.DOWN) Color.Gray else if (card.state == Face.MATCHED) Color.Green else Color.Black

    Box(modifier = Modifier.size(100.dp).padding(4.dp).clickable(onClick = onClick).background(bgColor)) {
        Text(text = card.pair.toString())
    }
}