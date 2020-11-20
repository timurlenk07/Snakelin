package com.snakelin.model

import com.snakelin.game.*
import java.util.*

object SnakelinModel {
    val options = SnakelinOptions()
    val highScores = mutableListOf<Score>()
    val resolution = doubleArrayOf(640.0, 480.0)
    var currentGame: GameState = resetGame()

    init {
        // TODO: load options
    }
}

fun SnakelinModel.resetGame(): GameState {
    currentGame = GameState(
            3,
            Snake(Point(2, 2), arrayListOf(Point(2, 1), Point(2, 0))),
            Apple(Point(1, 1)),
            PlayStatus.NOT_STARTED,
    )
    return currentGame
}

data class SnakelinOptions(
        val language: Locale = Locale.getDefault(),
        val gameSpeed: Int = 400,
)
