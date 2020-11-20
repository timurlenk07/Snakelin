package com.snakelin.model

import com.snakelin.game.GameState
import com.snakelin.game.PlayStatus
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


fun SnakelinModel.saveGame() {
    val stateString = Json.encodeToString(currentGame)
    File("checkpoint.json").writeText(stateString)
}

fun SnakelinModel.loadGame() {
    val ckptFile = File("checkpoint.json")
    if (ckptFile.exists()) {
        val stateString = ckptFile.readText()
        currentGame = Json.decodeFromString<GameState>(stateString)
        currentGame.status = PlayStatus.PAUSED
        ckptFile.delete()
    } else {
        println("No checkpoint found.")
    }
}
