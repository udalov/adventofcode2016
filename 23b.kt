fun main(args: Array<String>) {
    val lines = arrayListOf<MutableList<String>>()
    while (true) {
        lines.add(readLine()?.split(" ")?.toMutableList() ?: break)
    }

    var i = 0
    val reg = hashMapOf<Char, Int>()
    for (c in 'a'..'d') reg[c] = 0
    reg['a'] = 12

    fun String.int(): Int = try {
        toInt()
    } catch (e: Exception) {
        reg[single()]!!
    }

    while (i in lines.indices) {
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

    println(reg['a'])
}
