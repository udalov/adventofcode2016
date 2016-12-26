val regex = "Disc #[0-9]+ has ([0-9]+) positions; at time=0, it is at position ([0-9]+).".toRegex()

fun main(args: Array<String>) {
    val a = arrayListOf<Pair<Int, Int>>()
    while (true) {
        val line = readLine() ?: break
        val (m, k) = regex.matchEntire(line)!!.destructured
        a.add(m.toInt() to k.toInt())
    }

    var ans = 0L
    while (true) {
        if (a.withIndex().all { (index, pair) -> 
            val (m, k) = pair
            (ans + index + 1 + k) % m == 0L
        }) break
        ans++
    }

    println(ans)
}
