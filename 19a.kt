val n = 3005290

fun main(args: Array<String>) {
    val a = IntArray(n) { 1 }
    var i = 0
    while (true) {
        if (a[i] == n) break

        var j = (i + 1) % n
        while (a[j] == 0) j = (j + 1) % n

        if (a[i] != 0) {
            a[i] += a[j]
            a[j] = 0
        }
        i = j
    }

    println(i + 1)
}
