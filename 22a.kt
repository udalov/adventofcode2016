val regex = "/dev/grid/node-x([0-9]+)-y([0-9]+)\\s+([0-9]+)T\\s+([0-9]+)T\\s+([0-9]+)T\\s+([0-9]+)%".toRegex()

data class Node(val x: Int, val y: Int, val size: Int, val used: Int, val avail: Int) {
    override fun equals(other: Any?) =
        other is Node && x == other.x && y == other.y
}

val a = Array(100) { Array<Node?>(100) { null } }

fun viable(n1: Node, n2: Node): Boolean {
    return n1.used != 0 && n1.used < n2.avail
}

fun main(args: Array<String>) {
    while (true) {
        val line = readLine() ?: break
        val m = regex.matchEntire(line) ?: continue
        val (xs, ys, size, used, avail) = m.destructured
        val x = xs.toInt()
        val y = ys.toInt()
        assert(a[x][y] == null) { a[x][y].toString() }
        a[x][y] = Node(x, y, size.toInt(), used.toInt(), avail.toInt())
    }

    var ans = 0

    for (nodes1 in a) {
        for (node1 in nodes1) {
            if (node1 == null) continue
            for (nodes2 in a) {
                for (node2 in nodes2) {
                    if (node2 == null) continue
                    if (node1 == node2) continue
                    if (viable(node1, node2)) ans++
                }
            }
        }
    }

    println(ans)
}
