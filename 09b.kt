fun main(args: Array<String>) {
    var ans = 0L
    while (true) {
        val line = readLine() ?: break

        fun go(begin: Int, end: Int): Long {
            if (begin == end) return 0L
            if (line[begin] != '(') return go(begin + 1, end) + 1

            var i = begin
            val j = line.indexOf(')', i)
            val (len, rep) = line.substring(i + 1, j).split("x").map(String::toInt)
            i = j + 1

            return rep * go(i, i + len) + go(i + len, end)
        }

        ans += go(0, line.length)
    }
    println(ans)
}
