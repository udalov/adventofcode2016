val regex = "/dev/grid/node-x([0-9]+)-y([0-9]+)\\s+([0-9]+)T\\s+([0-9]+)T\\s+([0-9]+)T\\s+([0-9]+)%".toRegex()

data class Node(val x: Int, val y: Int, val size: Int, val used: Int) {
    val avail: Int get() = size - used

    override fun equals(other: Any?) =
        other is Node && x == other.x && y == other.y
}

val n = 36
val m = 30
val a = Array(n) { Array<Node?>(n) { null } }

fun main(args: Array<String>) {
    while (true) {
        val line = readLine() ?: break
        val m = regex.matchEntire(line) ?: continue
        val (xs, ys, size, used) = m.destructured
        val x = xs.toInt()
        val y = ys.toInt()
        assert(a[x][y] == null) { a[x][y].toString() }
        a[x][y] = Node(x, y, size.toInt(), used.toInt())
    }

    for (y in 0..m - 1) {
        for (x in 0..n - 1) {
            val node = a[x][y] ?: error("No node at $x $y")
            print(when {
                x == n - 1 && y == 0 -> 'G'
                node.size >= 500 -> '#'
                node.used == 0 -> '_'
                else -> '.'
            })
        }
        println()
    }

    // The single free node is here
    val (fx, fy) = 35 to 27
    for (x in 0..n - 1) for (y in 0..m - 1) assert((a[x][y]!!.used == 0) == (x == fx && y == fy))

    // Number of moves to transfer the free node to (n - 1, 0), just to the left of the goal
    val premoves = (fx - 1) + fy + (n - 3)

    // 5 moves to swap empty with goal, and place empty to the left of the goal again
    println(premoves + (n - 2) * 5 + 1)
}
