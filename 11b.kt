import java.util.*

/*
k, k + 1 -- microchip, generator

0, 1 -- promethium
2, 3 -- cobalt
4, 5 -- curium
6, 7 -- ruthenium
8, 9 -- plutonium
10, 11 -- elerium
12, 13 -- dilithium

0..13 -- first floor
14..27 -- second floor
28..41 -- third floor
42..55 -- fourth floor
56..57 -- elevator position
*/
val startState = (
    ((1L shl 0) + (1L shl 1) + (1L shl 10) + (1L shl 11) + (1L shl 12) + (1L shl 13)) +
    (((1L shl 3) + (1L shl 5) + (1L shl 7) + (1L shl 9)) shl 14) +
    (((1L shl 2) + (1L shl 4) + (1L shl 6) + (1L shl 8)) shl 28)
).toLong()

val finalState = (
    ((1L shl 14) - 1) shl 42
) + (3L shl 56)

val stuff = arrayOf("PrM", "PrG", "CoM", "CoG", "CuM", "CuG", "RuM", "RuG", "PlM", "PlG", "ElM", "ElG", "DiM", "DiG")

fun Long.has(bit: Int): Boolean = this and (1L shl bit) != 0L
fun Long.flip(bit: Int): Long = this xor (1L shl bit)

fun printState(state: Long) {
    val elevator = (state shr 56).toInt()
    for (floor in 3 downTo 0) {
        val level = state shr (floor * 14)
        print("F${floor + 1} ")
        if (floor == elevator) print(" E ") else print(" . ")
        for ((i, s) in stuff.withIndex()) {
            if (level.has(i)) print(s) else print(" . ")
            print(" ")
        }
        println()
    }
}

fun isValid(state: Long): Boolean {
    for (floor in 3 downTo 0) {
        val level = state shr (floor * 14)
        var hasUnconnectedChip = false
        for (i in 0..6) {
            if (level.has(i * 2) && !level.has(i * 2 + 1)) { hasUnconnectedChip = true; break }
        }
        val hasGenerator = level and (0b10101010101010L) != 0L
        if (hasUnconnectedChip && hasGenerator) return false
    }
    return true
}

val upOrDown = intArrayOf(1, -1)
val result = LongArray(100500)

fun neighbors(state: Long): Int {
    val elevator = (state shr 56).toInt()
    val level = elevator * 14
    var ans = 0

    for (elevatorShift in upOrDown) {
        val newElevator = elevator + elevatorShift
        if (newElevator !in 0..3) continue
        val base = state xor (elevator.toLong() shl 56) xor (newElevator.toLong() shl 56)
        val newLevel = newElevator * 14
        for (i in 0..13) if (base.has(i + level)) {
            val new = base.flip(i + level).flip(i + newLevel)
            if (isValid(new)) result[ans++] = new

            for (j in 0..13) if (i < j && base.has(j + level)) {
                val other = base.flip(i + level).flip(j + level).flip(i + newLevel).flip(j + newLevel)
                if (isValid(other)) result[ans++] = other
            }
        }
    }

    return ans
}

fun main(args: Array<String>) {
    val q = ArrayList<Long>()
    val d = HashMap<Long, Int>()
    var qb = 0
    q.add(startState)
    d[startState] = 0
    while (qb < q.size) {
        val state = q[qb++]
        if (state == finalState) break
        val dist = d[state]!!
        val n = neighbors(state)
        for (i in 0..n - 1) {
            val next = result[i]
            if (!d.containsKey(next)) {
                d[next] = dist + 1
                q.add(next)
            }
        }
    }

    /*
    printState(startState)
    println()
    for (s in neighbors(startState)) {
        printState(s)
        println()
    }
    */

    println(d[finalState])
}
