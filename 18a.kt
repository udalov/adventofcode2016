val rows = 40

fun go(s: String): String {
    return CharArray(s.length) { i ->
        val left = s.getOrNull(i - 1) == '^'
        val center = s[i] == '^'
        val right = s.getOrNull(i + 1) == '^'
        if ((left && center && !right) || (center && right && !left) ||
            (left && !center && !right) || (right && !center && !left)) '^' else '.'
    }.joinToString("")
}

fun main(args: Array<String>) {
    val lines = arrayListOf<String>(readLine()!!)
    repeat(rows - 1) { lines.add(go(lines.last())) }
    println(lines.sumBy { line -> line.sumBy { c -> if (c == '.') 1 else 0 } })
}
