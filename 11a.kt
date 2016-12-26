import java.util.*

/*
k, k + 1 -- microchip, generator

0, 1 -- promethium
2, 3 -- cobalt
4, 5 -- curium
6, 7 -- ruthenium
8, 9 -- plutonium

0..9 -- first floor
10..19 -- second floor
20..29 -- third floor
30..39 -- fourth floor
40..41 -- elevator position
*/
val startState = (
    ((1 shl 0) + (1 shl 1)) +
    (((1 shl 3) + (1 shl 5) + (1 shl 7) + (1 shl 9)) shl 10) +
    (((1 shl 2) + (1 shl 4) + (1 shl 6) + (1 shl 8)) shl 20)
).toLong()

val finalState = (
    ((1L shl 10) - 1) shl 30
) + (3L shl 40)

val stuff = arrayOf("PrM", "PrG", "CoM", "CoG", "CuM", "CuG", "RuM", "RuG", "PlM", "PlG")

fun Long.has(bit: Int): Boolean = this and (1L shl bit) != 0L
fun Long.flip(bit: Int): Long = this xor (1L shl bit)

fun printState(state: Long) {
    val elevator = (state shr 40).toInt()
    for (floor in 3 downTo 0) {
        val level = state shr (floor * 10)
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
        val level = state shr (floor * 10)
        val hasUnconnectedChip = (0..4).any { i -> level.has(i * 2) && !level.has(i * 2 + 1) }
        val hasGenerator = (0..4).any { i -> level.has(i * 2 + 1) }
        if (hasUnconnectedChip && hasGenerator) return false
    }
    return true
}

fun neighbors(state: Long): List<Long> {
    val elevator = (state shr 40).toInt()
    val result = ArrayList<Long>()
    val level = elevator * 10

    for (elevatorShift in intArrayOf(1, -1)) {
        val newElevator = elevator + elevatorShift
        if (newElevator !in 0..3) continue
        val base = state xor (elevator.toLong() shl 40) xor (newElevator.toLong() shl 40)
        val newLevel = newElevator * 10
        for (i in 0..9) if (base.has(i + level)) {
            val new = base.flip(i + level).flip(i + newLevel)
            if (isValid(new)) result.add(new)

            for (j in 0..9) if (i < j && base.has(j + level)) {
                val other = base.flip(i + level).flip(j + level).flip(i + newLevel).flip(j + newLevel)
                if (isValid(other)) result.add(other)
            }
        }
    }

    return result
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
        for (next in neighbors(state)) {
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
