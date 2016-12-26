fun main(args: Array<String>) {
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val w = "DRUL"
    var x = 0
    var y = 0
    while (true) {
        for (c in readLine() ?: break) {
            val d = w.indexOf(c)
            x = Math.max(Math.min(x + dx[d], 2), 0)
            y = Math.max(Math.min(y + dy[d], 2), 0)
        }
        print(3 * x + y + 1)
    }
    println()
}
