package isel.tds;

import java.util.Objects


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
//class Date (val year: Int, val month: Int = 1, val day: Int = 1){
//    init{
//        require( year>0){"Invalid year"}
//        require(month>0 && month <=12) {"Invalid month"}
//        require(day>0 && day<=this.lastDayOfMonth) { "Invalid day"}
//    }
//
//    override fun equals(other: Any?): Boolean =
//        other is Date &&
//                year==other.year && month==other.month && day==other.day
//
//    override fun hashCode(): Int = (year * 12 + month) * 31 + day
//
//    override fun toString(): String = "$year-" + "%02d-%02d".format(month, day)
//}

private const val DAY_BITS = 5 // 0.31
private const val MONTH_BITS = 4 //0..15
private const val YEAR_BITS = 12 // 0..4k

@JvmInline
value class Date private constructor(private val bits: Int){
//    private val bits: Int =
//        (year shl ( DAY_BITS + MONTH_BITS)) or (month shl DAY_BITS) or day
    val year: Int get() = bits shr (DAY_BITS + MONTH_BITS)
    val month: Int get() = bits shr (DAY_BITS) and ((1 shl MONTH_BITS)-1)
    val day: Int get() = bits and ((1 shl DAY_BITS) -1)

    constructor(year: Int, month: Int = 1, day: Int = 1): this(
        (year shl ( DAY_BITS + MONTH_BITS)) or (month shl DAY_BITS) or day
    ){
        require( year>0){"Invalid year"}
        require(month>0 && month <=12) {"Invalid month"}
        require(day>0 && day<=this.lastDayOfMonth) { "Invalid day"}
    }

//    override fun equals(other: Any?): Boolean =
//        other is Date &&
//                year==other.year && month==other.month && day==other.day
//
//    override fun hashCode(): Int = (year * 12 + month) * 31 + day

    override fun toString(): String = "$year-" + "%02d-%02d".format(month, day)
}
val Date.isLeapYear: Boolean
    get() = year%4 == 0 && year%100!=0 || year%400==0

private val daysOfMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

private val Date.lastDayOfMonth: Int
    get() = if(month==2 && isLeapYear) 29 else daysOfMonth[month-1]
val Date.isLastDayOfMonth: Boolean
    get() = day == this.lastDayOfMonth

fun Date.addDays(daysToAdd: Int): Date = when{
    day + daysToAdd <= lastDayOfMonth -> Date(year, month, day + daysToAdd)
    month < 12 -> Date( year, month +1, 1).addDays(calculateRemainderDays(daysToAdd))
    else -> Date(year +1, 1, 1).addDays(calculateRemainderDays(daysToAdd))
}
//Kotlin with tailrec implements the recursin in an iterative way
tailrec fun Date.addDaysTailRec(daysToAdd: Int): Date = when{
    day + daysToAdd <= lastDayOfMonth -> Date(year, month, day + daysToAdd)
    month < 12 -> Date( year, month +1, 1).addDaysTailRec(calculateRemainderDays(daysToAdd))
    else -> Date(year +1, 1, 1).addDaysTailRec(calculateRemainderDays(daysToAdd))
}

private fun Date.calculateRemainderDays(daysToAdd: Int): Int
    = daysToAdd - (lastDayOfMonth - day + 1)

operator fun Date.plus(daysToAdd: Int): Date = this.addDays(daysToAdd)
operator fun Int.plus(date: Date): Date = date.addDays(this)

operator fun Date.compareTo(otherDate: Date): Int = when{
    year != otherDate.year -> year-otherDate.year
    month!=otherDate.month -> month - otherDate.month
    else -> day - otherDate.day
}









