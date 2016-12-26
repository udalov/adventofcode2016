fun main(args: Array<String>) {
    var s = "10001001100000001"
    val n = 35651584

    while (s.length < n) {
        s += "0" + s.reversed().map { c -> '0' + (1 - (c - '0')) }.joinToString("")
    }
    s = s.substring(0, n)

    while (s.length % 2 == 0) {
        s = (0..s.length / 2 - 1).map { i ->
            if (s[2 * i] == s[2 * i + 1]) '1' else '0'
        }.joinToString("")
    }

    println(s)
}
