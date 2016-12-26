fun main(args: Array<String>) {
    var ans = 0
    while (true) {
        val (a, b, c) = readLine()?.split(" +".toRegex())?.filter(String::isNotEmpty)?.map(String::toInt) ?: break
        if (a + b > c && a + c > b && b + c > a) ans++
    }
    println(ans)
}
