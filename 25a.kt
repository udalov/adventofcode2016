fun run(lines: List<MutableList<String>>, regA: Int, limit: Int): String {
    val ans = StringBuilder()

    var i = 0
    val reg = hashMapOf<Char, Int>()
    for (c in 'a'..'d') reg[c] = 0
    reg['a'] = regA

    fun String.int(): Int =
        if (last().isDigit()) toInt() else reg[single()]!!

    loop@ while (i in lines.indices) {
        val line = lines[i++]
        when (line.first()) {
            "cpy" -> {
                val (_, src, dst) = line
                if (dst.length == 1 && dst.single() in reg.keys) {
                    reg[dst.single()] = src.int()
                }
            }
            "inc", "dec" -> {
                val (_, src) = line
                reg[src.single()] = reg[src.single()]!! + (if (line.first() == "inc") 1 else -1)
            }
            "out" -> {
                val (_, src) = line
                ans.append(src.int())
                if (ans.length == limit) break@loop
            }
            "jnz" -> {
                val (_, src, value) = line
                if (src.int() != 0) i = (i - 1) + value.int()
            }
            "tgl" -> {
                val (_, value) = line
                val g = lines.getOrNull((i - 1) + value.int())
                when (g?.size) {
                    2 -> g[0] = if (g[0] == "inc") "dec" else "inc"
                    3 -> g[0] = if (g[0] == "jnz") "cpy" else "jnz"
                    null -> {}
                    else -> error("Bad instruction: $g")
                }
            }
        }
    }

    return ans.toString()
}

fun main(args: Array<String>) {
    val lines = arrayListOf<MutableList<String>>()
    while (true) {
        lines.add(readLine()?.split(" ")?.toMutableList() ?: break)
    }

    for (regA in 1..Int.MAX_VALUE) {
        val s = run(lines, regA, 80)
        println("$regA $s")
        if (s.withIndex().all { (i, c) -> (i % 2 == 0) == (c == '0') }) {
            println(regA)
            break
        }
    }
}
