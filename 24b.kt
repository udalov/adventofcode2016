val dx = intArrayOf(1, 0, -1, 0)
val dy = intArrayOf(0, 1, 0, -1)

fun main(args: Array<String>) {
    val a = arrayListOf<BooleanArray>()
    val pos = hashMapOf<Int, Int>()
    while (true) {
        val line = readLine() ?: break
        for ((index, c) in line.withIndex()) {
            if (c.isDigit()) pos[c - '0'] = (a.size shl 8) + index
        }
        a.add(line.map { c -> c == '#' }.toBooleanArray())
    }

    val n = a.size
    val m = a.first().size
    val k = pos.keys.max()!!

    fun runBfs(start: Int): Map<Int, Int> {
        val dist = hashMapOf<Int, Int>()
        dist[start] = 0
        val q = arrayListOf<Int>()
        var qb = 0
        q.add(start)
        while (qb < q.size) {
            val v = q[qb++]
            val x = v shr 8
            val y = v and 255
            for (d in 0..3) {
                val xx = x + dx[d]
                val yy = y + dy[d]
                if (xx !in 0..n - 1 || yy !in 0..m - 1) continue
                if (a[xx][yy]) continue
                val w = (xx shl 8) + yy
                if (w in dist) continue
                dist[w] = dist[v]!! + 1
                q.add(w)
            }
        }
        return dist
    }

    val len = Array(k + 1) { IntArray(k + 1) }
    for (start in 0..k) {
        val dist = runBfs(pos[start]!!)
        for (end in 0..k) {
            len[start][end] = dist[pos[end]!!]!!
        }
    }

    var best = Int.MAX_VALUE
    val order = IntArray(k)

    fun go() {
        var cur = 0
        for (x in 0..k - 1) {
            cur += len[order.getOrNull(x - 1) ?: 0][order[x]]
        }
        cur += len[order.last()][0]
        best = Math.min(best, cur)
    }

    fun rec(i: Int, used: Int) {
        if (i == k) {
            go()
            return
        }
        for (j in 0..k - 1) {
            if (used and (1 shl j) == 0) {
                order[i] = j + 1
                rec(i + 1, used or (1 shl j))
            }
        }
    }

    rec(0, 0)
    println("$n $m $k")
    println(best)
}
