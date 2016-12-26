import java.util.*

val salt = "lpvhkcbi"
val n = 4
val dx = intArrayOf(-1, 1, 0, 0)
val dy = intArrayOf(0, 0, -1, 1)
val dc = charArrayOf('U', 'D', 'L', 'R')

fun md5(path: String): String {
    val args = listOf("md5", "-q", "-s", "$salt$path")
    return ProcessBuilder(args).start().apply { waitFor() }.inputStream.reader().readLines().single()
}

data class Pos(val x: Int, val y: Int, val path: String)

fun main(args: Array<String>) {
    val q = ArrayList<Pos>()
    var qb = 0
    q.add(Pos(0, 0, ""))

    var ans: String? = null

    while (qb < q.size) {
        val (x, y, path) = q[qb++]
        if (x == 3 && y == 3) {
            ans = path
            continue
        }
        val s = md5(path)
        for (d in 0..3) {
            val xx = x + dx[d]
            val yy = y + dy[d]
            if (xx < 0 || yy < 0 || xx >= n || yy >= n) continue
            if (s[d] !in 'b'..'f') continue
            q.add(Pos(xx, yy, path + dc[d]))
        }
    }

    println(ans)
    println(ans?.length)
}
