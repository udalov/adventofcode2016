val n = 3005290

fun main(args: Array<String>) {
    val next = IntArray(n) { i -> (i + 1) % n }
    val prev = IntArray(n) { i -> (i + n - 1) % n }
    val a = IntArray(n) { 1 }
    var i = 0
    var alive = n
    var between = alive / 2
    var j = i + between
    while (i != j) {
        a[i] += a[j]
        alive--

        val pj = prev[j]
        val nj = next[j]
        next[pj] = nj
        prev[nj] = pj

        i = next[i]
        j = nj
        between--

        while (between < alive / 2) {
            j = next[j]
            between++
        }
    }

    assert(alive == 1) { alive }

    println(i + 1)
}
