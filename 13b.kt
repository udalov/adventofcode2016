import java.util.*

val N = 1024

val dx = intArrayOf(1, 0, -1, 0)
val dy = intArrayOf(0, 1, 0, -1)

val a = Array(N) { x ->
    BooleanArray(N) { y ->
        val s = x*x + 3*x + 2*x*y + y + y*y + 1364
        Integer.bitCount(s) % 2 == 1
    }
}

fun main(args: Array<String>) {
    /*
    for (i in 0..9) {
        for (j in 0..9) print(if (a[j][i]) "#" else ".")
        println()
    }
    */

    val q = ArrayList<Int>()
    val dist = HashMap<Int, Int>()
    var qb = 0
    val start = (1 shl 10) + 1
    var ans = 0
    q.add(start)
    dist[start] = 0
    while (qb < q.size) {
        val cur = q[qb++]
        if (dist[cur]!! <= 50) ans++
        val x = cur shr 10
        val y = cur and 1023
        for (d in 0..3) {
            val xx = x + dx[d]
            val yy = y + dy[d]
            if (xx !in 0..N - 1 || yy !in 0..N - 1) continue
            if (a[xx][yy]) continue
            val new = (xx shl 10) + yy
            if (new in dist) continue
            dist[new] = dist[cur]!! + 1
            q.add(new)
        }
    }

    println(ans)
}
