package com.snakelin.model

import com.snakelin.game.GameState
import tornadofx.*
import java.util.*

object SnakelinModel {
    val options = SnakelinOptions(Locale.getDefault())
    var highScores = intArrayOf()
    val resolution = doubleArrayOf(480.0, 360.0)
    val currentGame = GameState()

    init {
        // TODO: load options
        // TODO: load high scores
    }
}

data class SnakelinOptions(
        val language: Locale
)
