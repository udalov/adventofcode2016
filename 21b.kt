val s = "abcdefgh".toCharArray()
val n = s.size

abstract class Action(regex: String) {
    val regex = regex.toRegex()

    abstract fun run(s1: String, s2: String)
}

fun swap(i: Int, j: Int) {
    val t = s[i]
    s[i] = s[j]
    s[j] = t
}

object SwapPosition : Action("swap position ([0-9]+) with position ([0-9]+)") {
    override fun run(s1: String, s2: String) {
        swap(s1.toInt(), s2.toInt())
    }
}

object SwapLetter : Action("swap letter ([a-z]) with letter ([a-z])") {
    override fun run(s1: String, s2: String) {
        swap(s.indexOf(s1.single()), s.indexOf(s2.single()))
    }
}

object Rotate : Action("rotate ([a-z]+) ([0-9]+) steps?") {
    override fun run(s1: String, s2: String) {
        val shift = s2.toInt() * (if (s1 == "left") -1 else 1)
        val t = CharArray(n) { i -> s[(i - shift + n + n) % n] }
        for (i in s.indices) s[i] = t[i]
    }
}

object RotateBased : Action("rotate based on position of (letter) ([a-z])") {
    override fun run(s1: String, s2: String) {
        val i = s.indexOf(s2.single())
        Rotate.run("right", (1 + i + (if (i >= 4) 1 else 0)).toString())
    }
}

object Reverse : Action("reverse positions ([0-9]+) through ([0-9]+)") {
    override fun run(s1: String, s2: String) {
        val i = s1.toInt()
        val j = s2.toInt()
        for (k in 0..(j - i) / 2) {
            swap(i + k, j - k)
        }
    }
}

object Move : Action("move position ([0-9]+) to position ([0-9]+)") {
    override fun run(s1: String, s2: String) {
        var i = s1.toInt()
        val j = s2.toInt()
        val inc = if (j > i) 1 else -1
        while (i != j) {
            swap(i, i + inc)
            i += inc
        }
    }
}


fun main(args: Array<String>) {
    val rnd = java.util.Random(42)
    val allActions = listOf(SwapPosition, SwapLetter, Rotate, RotateBased, Reverse, Move)

    val lines = arrayListOf<Triple<Action, String, String>>()
    line@ while (true) {
        val line = readLine() ?: break

        for (action in allActions) {
            val matcher = action.regex.matchEntire(line)
            if (matcher != null) {
                val (s1, s2) = matcher.destructured
                lines.add(Triple(action, s1, s2))
                continue@line
            }
        }

        error("Unmatched line: $line")
    }

    while (true) {
        for (i in n downTo 2) {
            swap(i - 1, rnd.nextInt(i))
        }
        val cur = s.joinToString("")
        for ((action, s1, s2) in lines) {
            action.run(s1, s2)
        }
        if (s.joinToString("") == "fbgdceah") {
            println(cur)
            break
        }
    }
}
