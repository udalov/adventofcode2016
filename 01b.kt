fun main(args: Array<String>) {
    val dx = intArrayOf(0, -1, 0, 1)
    val dy = intArrayOf(1, 0, -1, 0)
    var dir = 0
    var x = 0
    var y = 0
    val used = hashSetOf<Pair<Int, Int>>()
    outer@ for (s in readLine()!!.split(", ".toRegex())) {
        if (s[0] == 'R') dir += 3 else dir++
        dir = dir % 4
        val l = s.substring(1).toInt()
        for (i in 1..l) {
            x += dx[dir]
            y += dy[dir]
            if (!used.add(x to y)) break@outer
        }
    }
    println(Math.abs(x) + Math.abs(y))
}
