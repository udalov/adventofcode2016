fun main(args: Array<String>) {
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val w = "DRUL"
    val s = "..........1.....234...56789...ABC.....D.........."
    val a = (0..6).map { i -> s.substring(i * 7, i * 7 + 7) }
    var x = 4
    var y = 1
    while (true) {
        for (c in readLine() ?: break) {
            val d = w.indexOf(c)
            val (xx, yy) = (x + dx[d]) to (y + dy[d])
            if (a[xx][yy] != '.') {
                x = xx
                y = yy
            }
        }
        print(a[x][y])
    }
    println()
}
