package com.snakelin.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class Score (
    val score: Int,
    val name: String = "NO_NAME",
    // TODO: maybe add date as well
) : Comparable<Score> {
    override fun compareTo(other: Score): Int {
        return this.score.compareTo(other.score)
    }
}

fun SnakelinModel.saveHighScores() {
    val scoresString = Json.encodeToString(highScores)
    File("highscores.json").writeText(scoresString)
}

fun SnakelinModel.loadHighScores() {
    val highScoreFile = File("highscores.json")
    if (highScoreFile.exists()) {
        val highScoreString = highScoreFile.readText()
        val loadedHighScores = Json.decodeFromString<List<Score>>(highScoreString)
        highScores.clear()
        highScores.addAll(loadedHighScores)
    } else {
        println("No scores found.")
    }
}
