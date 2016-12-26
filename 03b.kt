fun main(args: Array<String>) {
    var ans = 0
    while (true) {
        fun read() = readLine()?.split(" +".toRegex())?.filter(String::isNotEmpty)?.map(String::toInt)
        val lines = listOf(read() ?: break, read() ?: break, read() ?: break)
        for (i in 0..2) {
            val (a, b, c) = (0..2).map { j -> lines[j][i] }
            if (a + b > c && a + c > b && b + c > a) ans++
        }
    }
    println(ans)
}
