fun main(args: Array<String>) {
    val list = arrayListOf<Pair<Long, Long>>()
    while (true) {
        val line = readLine() ?: break
        val (l, r) = line.split("-").map(String::toLong)
        list.add(l to r)
    }
    list.sortBy { (l) -> l }

    var max = 0L
    for ((l, r) in list) {
        if (l > max + 1) {
            println(max + 1)
            break
        }
        max = Math.max(max, r)
    }
}
