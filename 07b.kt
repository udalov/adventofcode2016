fun main(args: Array<String>) {
    fun getAbas(s: String, bab: Boolean) = (0..s.length - 3)
            .filter { i -> s[i] == s[i + 2] && s[i] != s[i + 1] }
            .map { i -> if (bab) (s[i + 1] to s[i]) else (s[i] to s[i + 1]) }

    var ans = 0
    while (true) {
        val line = readLine() ?: break
        val parts = line.split("\\[|\\]".toRegex()).withIndex()
        val even = parts.filter { (i, _) -> i % 2 == 0 }.flatMap { getAbas(it.value, false) }
        val odd = parts.filter { (i, _) -> i % 2 == 1 }.flatMap { getAbas(it.value, true) }
        if (even.intersect(odd).isNotEmpty()) ans++
    }
    println(ans)
}
