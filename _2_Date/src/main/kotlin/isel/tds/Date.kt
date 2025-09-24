package isel.tds;


//class Date {
//    val day: Int
//    val month: Int
//    val year: Int
//
//    constructor(y:Int, m:Int, d:Int){
//        year = y
//        month = m
//        day = d
//    }
//}

//class Date (val year: Int, val month: Int, val day: Int){
//
//    constructor(y: Int, m: Int): this(y, m, 1)
//    constructor(y: Int): this(y, 1, 1)
//}

//class Date (val year: Int, val month: Int = 1, val day: Int = 1){
//    val isLeapYear: Boolean
//        get() = year%4 == 0 && year%100!=0 || year%400==0
//
//    init{
//        require( year>0){"Invalid year"}
//        require(month>0) {"Invalid month"}
//        require(day>0) { "Invalid day"}
//
//        check(year>0){"Invalid year"}
//    }
//}
class Date (val year: Int, val month: Int = 1, val day: Int = 1){
    init{
        require( year>0){"Invalid year"}
        require(month>0 && month <=12) {"Invalid month"}
        require(day>0 && day<=this.lastDayOfMonth) { "Invalid day"}
    }
}
val Date.isLeapYear: Boolean
    get() = year%4 == 0 && year%100!=0 || year%400==0

private val daysOfMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

private val Date.lastDayOfMonth: Int
    get() = if(month==2 && isLeapYear) 29 else daysOfMonth[month-1]
val Date.isLastDayOfMonth: Boolean
    get() = day == this.lastDayOfMonth

fun Date.addDays(daysToAdd: Int): Date = Date(1,1,1)

operator fun Date.plus(daysToAdd: Int): Date = Date(1,1,1)
