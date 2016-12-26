fun main(args: Array<String>) {
    val a = Array(50) { BooleanArray(6) }
    while (true) {
        val line = readLine() ?: break
        if (line.startsWith("rect")) {
            val (n, m) = line.substring(5).split("x").map(String::toInt)
            for (i in 0..n - 1) {
                for (j in 0..m - 1) {
                    a[i][j] = true
                }
            }
        } else if (line.startsWith("rotate row y=")) {
            val (y, shift) = line.substring(13).split(" by ").map(String::toInt)
            val b = BooleanArray(a.size)
            for (i in a.indices) b[(i + shift) % a.size] = a[i][y]
            for (i in a.indices) a[i][y] = b[i]
        } else if (line.startsWith("rotate column x=")) {
            val (x, shift) = line.substring(16).split(" by ").map(String::toInt)
            val b = BooleanArray(a.first().size)
            for (i in a.first().indices) b[(i + shift) % a.first().size] = a[x][i]
            for (i in a.first().indices) a[x][i] = b[i]
        }
    }
    println(a.sumBy { b -> b.sumBy { if (it) 1 else 0 } })
}
