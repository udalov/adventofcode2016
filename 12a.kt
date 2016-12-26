fun main(args: Array<String>) {
    val lines = arrayListOf<List<String>>()
    while (true) {
        lines.add(readLine()?.split(" ") ?: break)
    }

    var i = 0
    val reg = hashMapOf<Char, Int>()
    for (c in 'a'..'d') reg[c] = 0
    while (i in lines.indices) {
        val line = lines[i++]
        when (line.first()) {
            "cpy" -> {
                val (_, src, dst) = line
                if (src.length == 1 && !src.single().isDigit()) reg[dst.single()] = reg[src.single()]!!
                else reg[dst.single()] = src.toInt()
            }
            "inc", "dec" -> {
                val (_, src) = line
                reg[src.single()] = reg[src.single()]!! + (if (line.first() == "inc") 1 else -1)
            }
            "jnz" -> {
                val (_, src, value) = line
                if (reg[src.single()] != 0) i = (i - 1) + value.toInt()
            }
        }
    }

    println(reg['a'])
}
