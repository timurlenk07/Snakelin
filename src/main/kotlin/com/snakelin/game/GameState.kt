package com.snakelin.game

import kotlinx.serialization.Serializable

@Serializable
data class GameState(
        val mapSize: Int,
        val player: Snake,
        val apple: Apple,
        var status: PLAY_STATUS,
)

val GameState.score: Int
    get() {
        return player.getHealth()
    }