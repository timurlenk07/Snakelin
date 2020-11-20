package com.snakelin.model

import com.snakelin.game.GameEngine
import java.util.*
import tornadofx.*

object SnakelinModel {
    val options = SnakelinOptions()
    val highScores = mutableListOf<Score>()
    val resolution = doubleArrayOf(640.0, 480.0)
    var currentGame = GameEngine()

    init {
        // TODO: load options
    }
}

fun SnakelinModel.resetGame() {
    currentGame = GameEngine()
}

data class SnakelinOptions(
    val language: Locale = Locale.getDefault(),
    val gameSpeed: Int = 400,
)
