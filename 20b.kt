fun main(args: Array<String>) {
    val list = arrayListOf<Pair<Long, Long>>()
    while (true) {
        val line = readLine() ?: break
        val (l, r) = line.split("-").map(String::toLong)
        list.add(l to r)
    }
    list.sortBy { (l) -> l }

    var ans = 0L
    var max = 0L
    for ((l, r) in list) {
        ans += Math.max(l - max - 1, 0)
        max = Math.max(max, r)
    }
    println(ans)
}
