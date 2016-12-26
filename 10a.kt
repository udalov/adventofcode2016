import java.util.*

sealed class Location {
    data class Bot(val number: Int) : Location()
    data class Output(val number: Int) : Location()
}

data class Instr(val bot: Int, val lo: Location, val hi: Location)

class Bot(val number: Int, val chips: MutableSet<Int>)

val N = 250
val bots = Array<Bot>(N) { Bot(it, TreeSet()) }
val outputs = IntArray(N) { -1 }
val instrs = LinkedList<Instr>()

fun main(args: Array<String>) {
    var ans: Int? = null

    fun advance() {
        do {
            var updated = false
            for (instr in instrs) {
                if (bots[instr.bot].chips.size < 2) continue
                updated = true
                val (nbot, loc1, loc2) = instr
                instrs.remove(instr)
                val bot = bots[nbot]
                assert(bot.chips.size == 2) { "Bot $nbot has: ${bot.chips}" }
                val (lo, hi) = bot.chips.toList()
                if (lo == 17 && hi == 61) {
                    ans = nbot
                }
                bot.chips.clear()
                for ((loc, value) in arrayOf(loc1 to lo, loc2 to hi)) {
                    when (loc) {
                        is Location.Bot -> bots[loc.number].chips.add(value)
                        is Location.Output -> {
                            assert(outputs[loc.number] == -1) { "Output ${loc.number} has ${outputs[loc.number]}" }
                            outputs[loc.number] = value
                        }
                    }
                }
                break
            }
        } while (updated)
    }

    while (true) {
        val line = readLine() ?: break
        val m1 = "value ([0-9]+) goes to bot ([0-9]+)".toRegex().matchEntire(line)
        if (m1 != null) {
            val groups = m1.groups
            val value = groups[1]!!.value.toInt()
            val bot = groups[2]!!.value.toInt()
            bots[bot].chips.add(value)
            advance()
            continue
        }

        val m2 = "bot ([0-9]+) gives low to ([boput]+) ([0-9]+) and high to ([boput]+) ([0-9]+)".toRegex().matchEntire(line)
        if (m2 != null) {
            val groups = m2.groups
            val nbot = groups[1]!!.value.toInt()
            val arg1 = groups[3]!!.value.toInt()
            val lo = if (groups[2]!!.value == "bot") Location.Bot(arg1) else Location.Output(arg1)
            val arg2 = groups[5]!!.value.toInt()
            val hi = if (groups[4]!!.value == "bot") Location.Bot(arg2) else Location.Output(arg2)
            instrs.offer(Instr(nbot, lo, hi))
            advance()
            continue
        }

        error("Unmatched line: $line")
    }

    println("instrs left: ${instrs.size}")
    println("outputs: ${outputs.take(10)} ...")
    println(ans)
}
