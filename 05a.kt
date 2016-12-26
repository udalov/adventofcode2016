val N = 1000

fun main(args0: Array<String>) {
    val id = "uqwqemis"
    var ans = ""
    var i = 0L
    while (true) {
        println(i)
        val args = arrayListOf("md5", "-q")
        for (j in i..i + N - 1) {
            args.add("-s")
            args.add("$id$j")
        }
        i += N
        val lines = ProcessBuilder(args).start().apply { waitFor() }.inputStream.reader().readLines()
        for ((index, line) in lines.withIndex()) {
            if (line.startsWith("00000")) {
                println("${i + index} $line")
                ans += line[5]
                if (ans.length == 8) {
                    println(ans)
                    return
                }
            }
        }
    }
}
