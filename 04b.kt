fun main(args: Array<String>) {
    while (true) {
        val line = readLine() ?: break
        val ls = line.split("[\\-\\[\\]]".toRegex()).filter(String::isNotEmpty)
        val chars = hashMapOf<Char, Int>()
        for (t in ls.subList(0, ls.size - 2)) {
            for (c in t) chars[c] = (chars[c] ?: 0) + 1
        }
        val sector = ls[ls.size - 2].toInt()
        for (t in ls.subList(0, ls.size - 2)) {
            for (c in t) print('a' + (((c - 'a') + sector) % 26))
            print('-')
        }
        println(sector)
    }
}
