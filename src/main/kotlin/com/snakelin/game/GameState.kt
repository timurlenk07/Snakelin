package com.snakelin.game

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class GameState(
        val mapSize: Int,
        val player: Snake,
        val apple: Apple,
        @Transient var status: PlayStatus = PlayStatus.NOT_STARTED,
)

val GameState.score: Int
    get() {
        return player.getHealth()
    }