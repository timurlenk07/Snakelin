package com.snakelin.model

import com.snakelin.game.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


object SnakelinModel {
    var options = SnakelinOptions()
    val highScores = mutableListOf<Score>()
    val resolution = doubleArrayOf(640.0, 480.0)
    var currentGame: GameState = resetGame()
}

fun SnakelinModel.resetGame(): GameState {
    currentGame = GameState(
            3,
            Snake(Point(3 / 2, 3 / 2), arrayListOf(Point(3 / 2 + 1, 3 / 2), Point(3 / 2 + 1, 3 / 2 + 1))),
            Apple(Point(0, 0)),
            PlayStatus.NOT_STARTED,
    )
    return currentGame
}


@Serializable
data class SnakelinOptions(
        val gameSpeed: Int = 400,
)

fun SnakelinModel.saveOptions() {
    val optionsString = Json.encodeToString(options)
    File("options.json").writeText(optionsString)
}

fun SnakelinModel.loadOptions() {
    val optionsFile = File("options.json")
    if (optionsFile.exists()) {
        val optionsString = optionsFile.readText()
        options = Json.decodeFromString<SnakelinOptions>(optionsString)
    } else {
        println("No options found.")
    }
}
