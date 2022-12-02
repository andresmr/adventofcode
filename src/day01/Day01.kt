package day01

import readText

fun main() {

    fun parseInput(input:String) = input.split("\n\n").map { elf ->
        elf.lines().map { it.toInt() }
    }

    fun part1(input: String): Int {
        val data = parseInput(input)
        return data.maxOf { it.sum() }
    }

    fun part2(input: String): Int {
        val data = parseInput(input)

        return data
            .map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readText("day01","Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readText("day01","Day01")
    println(part1(input))
    println(part2(input))
}
