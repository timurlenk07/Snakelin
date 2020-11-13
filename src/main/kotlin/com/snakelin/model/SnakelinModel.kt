package com.snakelin.model

import com.snakelin.game.GameEngine
import java.util.*

object SnakelinModel {
    val options = SnakelinOptions(Locale.getDefault())
    var highScores = intArrayOf()
    val resolution = doubleArrayOf(640.0, 480.0)
    var currentGame = GameEngine()

    init {
        // TODO: load options
        // TODO: load high scores
    }
}

fun SnakelinModel.resetGame() {
    currentGame = GameEngine()
}

data class SnakelinOptions(
    val language: Locale
)
