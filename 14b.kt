val salt = "ahsbgdzn"
val cache = hashMapOf<Int, String>()
val N = 1000

fun f(x: Int): String {
    cache[x]?.let { return it }

    var lines = (x..x + N - 1).map { i -> "$salt$i" }
    repeat(2017) {
        val args = arrayListOf("md5", "-q")
        for (line in lines) {
            args.add("-s")
            args.add(line)
        }
        lines = ProcessBuilder(args).start().apply { waitFor() }.inputStream.reader().readLines()
    }
    for ((index, line) in lines.withIndex()) {
        cache[x + index] = line
    }

    return cache[x]!!
}

fun String.triples(): List<Char> {
    for (i in 0..length - 3) {
        if (this[i] == this[i + 1] && this[i] == this[i + 2]) {
            return listOf(this[i])
        }
    }
    return emptyList()
}

fun String.hasFive(c: Char): Boolean {
    for (i in 0..length - 5) {
        if (this[i] != c) continue
        var b = true
        for (j in i + 1..i + 4) b = b && c == this[j]
        if (b) return true
    }
    return false
}

fun main(args: Array<String>) {
    var n = 1
    for (i in 1..Int.MAX_VALUE) {
        val triples = f(i).triples()
        if (triples.any { triple -> (i + 1..i + 1000).any { x -> f(x).hasFive(triple) } }) {
            println("$i ${n++}")
        }
        if (n > 64) break
    }
}
