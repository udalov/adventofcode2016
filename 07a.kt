fun main(args: Array<String>) {
    fun hasAbba(s: String) =
            (0..s.length - 4).any { i -> s[i] == s[i + 3] && s[i + 1] == s[i + 2] && s[i] != s[i + 1] }

    var ans = 0
    while (true) {
        val line = readLine() ?: break
        val parts = line.split("\\[|\\]".toRegex()).withIndex()
        if (parts.any { (i, s) -> i % 2 == 1 && hasAbba(s) }) continue
        if (parts.any { (_, s) -> hasAbba(s) }) ans++
    }
    println(ans)
}
