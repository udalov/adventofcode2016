val N = 1000

fun main(args0: Array<String>) {
    val id = "uqwqemis"
    var ans = "________".toCharArray()
    var i = 0L
    while (true) {
        val args = arrayListOf("md5", "-q")
        for (j in i..i + N - 1) {
            args.add("-s")
            args.add("$id$j")
        }
        i += N
        val lines = ProcessBuilder(args).start().apply { waitFor() }.inputStream.reader().readLines()
        for ((index, line) in lines.withIndex()) {
            if (line.startsWith("00000")) {
                val pos = line[5] - '0'
                if (pos < 0 || pos > 7 || ans[pos] != '_') continue
                println("${i + index} $line ${ans.joinToString("")}")
                ans[pos] = line[6]
                if (ans.none { it == '_' }) {
                    println(ans.joinToString(""))
                    return
                }
            }
        }
    }
}
