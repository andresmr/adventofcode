package day02

import readText

fun main() {
    val inputTest = readText("day02", "Day02_test")
    check(part1(inputTest) == 15)
    check(part2(inputTest) == 12)

    val input = readText("day02", "Day02")
    println(part2(input))
}

fun part2(input: String): Int {
    val games = parseInputPart2(input)
    val totalScore = games.sumOf {
        getScore(it)
    }
    println(totalScore)
    return totalScore
}

fun part1(input: String): Int {
    val games = parseInput(input)
    val totalScore = games.sumOf {
        getScore(it)
    }
    return totalScore
}

fun getScore(game: List<Hand>): Int {
    var finalScore = 0
    finalScore += getRoundScore(game)
    finalScore += getShapeScore(game[1])
    return finalScore
}

fun getShapeScore(hand: Hand): Int = when (hand) {
    is paper -> 2
    is rock -> 1
    is scissors -> 3
}

fun getRoundScore(game: List<Hand>): Int {
    return when (game[1]) {
        is paper -> when (game[0]) {
            is paper -> 3
            is rock -> 6
            is scissors -> 0
        }

        is rock -> when (game[0]) {
            is paper -> 0
            is rock -> 3
            is scissors -> 6
        }

        is scissors -> when (game[0]) {
            is paper -> 6
            is rock -> 0
            is scissors -> 3
        }
    }
}

private fun parseInput(input: String) = input.split("\n").map {
    it.split(" ").map { playerHand ->
        when (playerHand) {
            "A", "X" -> rock()
            "B", "Y" -> paper()
            "C", "Z" -> scissors()
            else -> error("Check your inputs")
        }
    }
}

fun parseInputPart2(input: String) = input.split("\n").map {
    val game = it.split(" ")
        game.mapIndexed { index, string ->
        when (index) {
            0 -> when (string) {
                "A", "X" -> rock()
                "B", "Y" -> paper()
                "C", "Z" -> scissors()
                else -> error("Check your inputs")
            }
            else -> {
                val theirHand = game[0]
                val desiredResult = string
                when (theirHand to desiredResult) {
                    "A" to "X", "B" to "Z", "C" to "Y" -> scissors()
                    "A" to "Y", "B" to "X", "C" to "Z"-> rock()
                    "A" to "Z", "B" to "Y", "C" to "X" -> paper()
                    else -> error("Check your inputs")
                }
            }
        }
    }
}

sealed class Hand()
class rock : Hand()
class paper : Hand()
class scissors : Hand()
