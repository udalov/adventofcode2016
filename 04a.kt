fun main(args: Array<String>) {
    var ans = 0
    while (true) {
        val line = readLine() ?: break
        val ls = line.split("[\\-\\[\\]]".toRegex()).filter(String::isNotEmpty)
        val chars = hashMapOf<Char, Int>()
        for (t in ls.subList(0, ls.size - 2)) {
            for (c in t) chars[c] = (chars[c] ?: 0) + 1
        }
        if (chars.entries.sortedBy { -10000 * it.value + it.key.toInt() }.take(5).map { it.key } == ls.last().toCharArray().toList()) {
            ans += ls[ls.size - 2].toInt()
        }
    }
    println(ans)
}
