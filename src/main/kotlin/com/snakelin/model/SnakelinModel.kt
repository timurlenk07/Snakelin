package com.snakelin.model

import com.snakelin.game.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


object SnakelinModel {
    var options = SnakelinOptions()
        set(value) {
            field = value
            resetGame()
        }
    val highScores = mutableListOf<Score>()
    val resolution = doubleArrayOf(640.0, 480.0)
    var currentGame: GameState = resetGame()
}

fun SnakelinModel.resetGame(): GameState {
    val cp = options.mapSize / 2
    currentGame = GameState(
            options.mapSize,
            Snake(Point(cp, cp), arrayListOf(Point(cp + 1, cp), Point(cp + 1, cp + 1))),
            Apple(Point(0, 0)),
            PlayStatus.NOT_STARTED,
    )
    return currentGame
}


@Serializable
data class SnakelinOptions(
        var gameSpeed: Int = 400,
        var mapSize: Int = 3,
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
