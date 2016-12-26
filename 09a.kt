fun main(args: Array<String>) {
    var ans = 0
    while (true) {
        val line = readLine() ?: break
        var i = 0
        var out = StringBuilder()
        while (i < line.length) {
            if (line[i] == '(') {
                val j = line.indexOf(')', i)
                val (len, rep) = line.substring(i + 1, j).split("x").map(String::toInt)
                i = j + 1
                repeat(rep) { out.append(line.substring(i, i + len)) }
                i += len
            } else {
                out.append(line[i])
                i++
            }
        }
        ans += out.toString().length
    }
    println(ans)
}
