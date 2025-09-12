package isel.tds

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    _01_helloWorld()

    _2_testValAndVars()

    _3_testBasicTypes()

    _4_testRanges()

    _5_testIfsAndWhens()

    _6_testCollections()
}

fun _3_testBasicTypes() {
    _31_testNumericTypes()

    _32_testTextTypes()


}

fun _32_testTextTypes() {
    val name = "Kotlin"
    println("Hello, $name!")

    fun getName(name: String): String {
        return name
    }
    println("Hello, ${getName(name)}!")

    val letter: Char = 'A'         // Single character, : Char can be omittedomited
    val tab = '\t'                 // Tab character
    val euro = '\u20AC'            // Unicode for symbol €
    println("|$tab|$letter|$euro") // --> |   |A|€


    val cA: Int = 'A'.code          // Unicode code of 'A'
    val cB = 'B'.toInt()            // Unicode code of 'B', Char.toInt() deprecated
    println("A code = $cA, B code = $cB")   // --> A code = 65, B code = 66

    val d: Char = 'A' + 3             // Char + Int = Char
    val y = 'Z' - 1                   // Char - Int = Char
    val dif: Int = 'Y' - 'D'          // Char - Char = Int
    println("'$y'-'$d' = $dif")       // --> 'Y'-'D' = 21

    val ln = '\n'               // Newline character
    val str = "Euro-\u20AC.$ln" // String terminated by a newline
    print(str)                  // --> Euro-€.    (like a println)

    val raw =
        """
A-\u20AC //not a comment
-B \n \t

--Useful for SQL, regex, HTML, XML, JSON, etc.
select * 
from my_table
where column = 'value'
"""
    println(raw)
//Indexação nas Strings
    val s = "Kotlin"
    val first = s[0]                        // Char at first index
    val last = s[s.length-1]                // Char at last index
    println("$s: first=$first, last=$last") // --> Kotlin: first=K, last=n

    //String concatenation
    val final = '!'
    val txt1 = s + " é fixe" + final  // Concatenation: "Kotlin é fixe!"
    println(txt1)
    val txt2 = "$s é fixe$final"      // String template: "Kotlin é fixe!"
    println(txt2)

    //String to numeric and vice versa
    println(126.toString() + " = 0x"+126.toString(16))    // --> 126 = 0x7e
    println("011".toInt().toString() + " + " +  "011".toByte(2).toString())              // --> 14
    println("011".toInt() + "011".toByte(2))              // --> 14
    println("3.4e4".toDouble() + 1)                       // --> 34001.0

    //Logical Values
    val ok: Boolean = true              // Explicit type can be omitted
    val notOk = false
    println("ok = $ok, notOk = $notOk") // --> ok = true, notOk = false

    val logic: Boolean = "TRUE".toBoolean()     // Convert a String to a Boolean
    val text = logic.toString()                 // Convert a Boolean to a String
    println("logic = $text")

    // Logical Evaluation
    val max = 36
    val b1: Boolean = 5 < max // b1 = true
    val res = firstBooleanExpression() || b1 || 30 >= max || lastBooleanExpression()// res = true, The right part of || is not evaluated
    println("b1 = $b1, res = $res")


}


fun firstBooleanExpression(): Boolean {
    println("firstBooleanExpression")
    return false
}

fun lastBooleanExpression(): Boolean {
    println("lastBooleanExpression")
    return true
}
fun _31_testNumericTypes() {
    val a: Int = 1
    val b: Long = 1234234
    val b2 = 12342L
    val c: Float = 1.0f
    val d: Double = 1.0
    val e: Char = 'a'
    val f: String = "a"
    val g: Boolean = true

    println("a: ${a}, b: $b, b2: $b2, c: $c, d: $d, e: $e, f: $f, g: $g")

    val h: Int? = null
    val i: Long? = null
    val j: Float? = null
    val k: Double? = null
    val l: Char? = null
    val m: String? = null
    val n: Boolean? = null

    println("h: $h, i: $i, j: $j, k: $k, l: $l, m: $m, n: $n")

    val o: Int? = 1
    val p: Long? = 1L
    val q: Float? = 1.0f
    val r: Double? = 1.0
    val s: Char? = 'a'
    val t: String? = "a"
    val u: Boolean? = true

    println("o: $o, p: $p, q: $q, r: $r, s: $s, t: $t, u: $u")

    //Hexadecimal format
    val x: Int               // Declaration without initialization
    x = 0x0F                // Initialize with hexadecimal value 3F unsigned
    println("x Hex in Dec = $x, in hex: ${x.toHexString()}")

    val y: Byte               // Declaration without initialization
    y = 0x0F                 // Initialize with hexadecimal value 3F unsigned
    println("y Hex in Dec = $y, in hex: ${y.toHexString()}, , in Long hex: ${y.toLong().toHexString()}")

    //Real values
    val realValue = 2.3e4        // Inferred as Double
    println("realValue = $realValue")
}

private fun _01_helloWorld() {
    val name = "Kotlin"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    println("Hello, " + name + "!")

    for (i in 1..5) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        println("i = $i")
    }
}

fun _2_testValAndVars() {
    val a =1
//    a=2 // Error: Val cannot be reassigned
    var b = 1
    b = 2
    println("a=$a, b=$b")

    class MyComplexA {
        val a = 1
        var b = 2
    }
    val myComplexA = MyComplexA()
//    myComplexA.a = 11
    myComplexA.b = 3

    println("myComplexA.a: ${myComplexA.a}, myComplexA.b: ${myComplexA.b}")

}

fun _4_testRanges() {
    //Ranges
    val max = 5
    val r1 = 1..max             // Inclusive range, Type IntRange is inferred
    println("range1 = $r1")     // --> range1 = 1..5
    val r2 =  1..<(max+1)       // Exclusive range
    println("$r1 == $r2 = ${r1==r2}")  // --> 1..5 == 1..5 = true

    //range operations
    val letters = 'A'..'Z'      // Range of chars, Type CharRange is inferred
    println('H' in letters)     // --> true, same as println('H'>='A' && 'H'<='Z')
    val total = letters.count()
    println("${letters.first}..${letters.last} has $total") // --> A..Z has 26

    // step
    val evens = 2..10 step 2     // Progression, Type IntProgression is inferred
    println(evens.count())       // --> 5
    println(3 in evens)          // --> false
    val odds = 9 downTo 0 step 2 // Decreasing progression
    println(odds.last)           // --> 1

    //Show downTo infix function

    //String ranges support "in" but not "count"
    val prices = 3.5..6.75     // Double range
    println(4.5 in prices)     // --> true

    val names = "ana".."pedro" // String range
    println("joão" in names)   // --> true
    println("rui" in names)    // --> false


    //Random numbers in range
    val dice = (1..6).random() // Random number in the range
    println("Dice = $dice")    // --> Dice = ...


}

fun _5_testIfsAndWhens() {
    println("if")
    val value: Int = (-10..10).random()
    if (value >= 0)                    // if to control the flow of the program
        println("$value is positive.")
    else
        println("$value is negative.")

    //If as an expression
    val value2: Int = (-10..10).random()
    val text = if (value2 >= 0) "positive" else "negative" // if as an expression
    println("$value2 is $text.")

    //When
    println("When")
    val value3: Int = (-20..20).random()
    when {
        value3 > 0 -> println("$value3 is positive.")
        value3 < 0 -> println("$value3 is negative.")
        else -> println("$value3 is zero.")
    }


    when {
        value3 !in -10..10 -> {
            println("Error.")
            println("$value3 is invalid.")
        }
        value3 > 0 -> println("$value3 is positive.")
        value3 < 0 -> println("$value3 is negative.")
    }
    //When with an expression
    when (val value5 = (-10..10).random()) {
        0 -> println("The value is zero.")
        2, 4, 6, 8, 10 -> println("$value5 is even.")
        in 1..10 -> println("$value5 is positive.")
        else -> println("$value5 is negative.")
    }

    //When as an expression
    val value5 = (-10..10).random()
    val result = when {
        value5 > 0 -> "positive"
        value5 < 0 -> "negative"
        else -> "zero"
    }
    println("$value5 is $result.")
}

fun _6_testCollections() {
    //-----------------List-----------------
    val names: List<String> = listOf("Pedro", "Ana", "João", "Maria", "Ana")
    println(names)                 // --> [Pedro, Ana, João, Maria, Ana]
    //[0] and [size-1]
    println("First name: ${names[0]}")            // --> First name: Pedro
    println("Last name: ${names[names.size-1]}")  // --> Last name: Ana
    // first and last
    println("First name: ${names.first()}")  // --> First name: Pedro
    println("Last name: ${names.last()}")    // --> Last name: Ana
    //in
    println("Maria" in names)    // --> true

    //Immutable List operations
    var numbers = listOf(5, 1, 3, 4, 3, 6, 7)
    numbers = numbers + listOf(2, 1) - 4  // A new List<Int> is created
    println(numbers)

    //Mutable List operations
    val mutableNames: MutableList<String> = mutableListOf("Ana", "Pedro", "Maria")
    mutableNames.add("Ana")        // Append at the end
    mutableNames.remove("Pedro")   // Remove the first found
    mutableNames[1] = "Isabel"     // Replace the element at index 1
    println(mutableNames)          // --> [Ana, Isabel, Ana]
    val readOnlyNames: List<String> = mutableNames

    //-----------------Set-----------------
    val fruit: Set<String> = setOf("maçã", "banana", "kiwi", "laranja", "kiwi")
    println(fruit)           // --> [maçã, banana, kiwi, laranja]

    println("banana" in fruit)    // --> true

    var moreFruit = fruit + setOf("pera", "banana") // A new Set<String> is created
    println(moreFruit)                              // --> [maçã, banana, kiwi, laranja, pera]
    moreFruit = moreFruit - "kiwi"                  // Set<String> - String = Set<String>
    println(moreFruit)                              // --> [maçã, banana, laranja, pera]

    //Mutable Set operations
    val mutableFruit: MutableSet<String> = mutableSetOf("maçã", "banana", "kiwi")
    println(mutableFruit)          // --> [maçã, banana, kiwi]
    mutableFruit.add("morango")    // Append at the end
    mutableFruit.remove("banana")  // Remove the first found
    println(mutableFruit)          // --> [maçã, kiwi, morango]
    val readOnlyFruit: Set<String> = mutableFruit

    //-----------------Map-----------------
    val p1= Pair("Pedro",48)
    val personAges: Map<String,Int> = mapOf(p1, "Ana" to 46, "Luis" to 27, "João" to 15)
    println(personAges)        // --> {Pedro=48, Ana=46, Luis=27, João=15}

    println(personAges.size)      // --> 4
    println(personAges["Luis"])   // --> 27
    // Key in Map
    println("Ana" in personAges)    // --> true

    //Key and values
    val keys: Set<String> = personAges.keys
    println(keys)               // --> [Pedro, Ana, Luis, João]
    println(personAges.values)  // --> [48, 46, 27, 15]

    //Map operations
    var m = mapOf("Pedro" to 48, "Ana" to 46, "Luis" to 27)
    m = m + ("João" to 15) - "Ana" + ("Pedro" to 49)
    println(m) // --> {Pedro=49, Luis=27, João=15}

    //Mutable Map operations
    val mutablePersonAges = mutableMapOf("Pedro" to 48, "Ana" to 46, "Luis" to 27)
    mutablePersonAges.put("João", 15)
    mutablePersonAges.remove("Ana")
    mutablePersonAges["Pedro"] = 49
    println(mutablePersonAges) // --> {Pedro=49, Luis=27, João=15}


}