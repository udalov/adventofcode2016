fun main(args: Array<String>) {
    val dx = intArrayOf(0, -1, 0, 1)
    val dy = intArrayOf(1, 0, -1, 0)
    var dir = 0
    var x = 0
    var y = 0
    for (s in readLine()!!.split(", ".toRegex())) {
        if (s[0] == 'R') dir += 3 else dir++
        dir = dir % 4
        val l = s.substring(1).toInt()
        x += l * dx[dir]
        y += l * dy[dir]
    }
    println(Math.abs(x) + Math.abs(y))
}
