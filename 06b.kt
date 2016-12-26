fun main(args: Array<String>) {
    val m = hashMapOf<Int, MutableList<Char>>()
    while (true) {
        val line = readLine() ?: break
        for (i in line.indices) {
            m.getOrPut(i) { arrayListOf() }.add(line[i])
        }
    }
    var i = 0
    while (m.containsKey(i)) {
        val l = m[i++]!!
        print(l.distinct().map { c -> c to l.count { it == c } }.minBy { it.second }!!.first)
    }
    println()
}
