package com.snakelin.model

import com.snakelin.game.GameEngine
import com.snakelin.game.Point
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


@Serializable
data class SavedGameState(
    val player_head: Point,
    val player_body: List<Point>,
    val apple_pos: Point,
    val mapSize: Int
)

fun GameEngine.fromSavedState(savedState: SavedGameState) : Boolean {
    if (this.mapSize != savedState.mapSize) {
        return false
    }
    this.player.head = savedState.player_head
    this.player.body.clear()
    this.player.body.addAll(savedState.player_body)
    this.apple.pos = savedState.apple_pos
    return true
}

fun GameEngine.toSavedState() : SavedGameState {
    return SavedGameState(
        player.head,
        player.body,
        apple.pos,
        mapSize
    )
}

fun SnakelinModel.saveGame() {
    val state = currentGame.toSavedState()
    val stateString = Json.encodeToString(state)
    File("checkpoint.json").writeText(stateString)
}

fun SnakelinModel.loadGame() {
    val ckptFile = File("checkpoint.json")
    if (ckptFile.exists()) {
        val stateString = ckptFile.readText()
        val state = Json.decodeFromString<SavedGameState>(stateString)
        currentGame.fromSavedState(state)
    } else {
        println("No checkpoint found.")
    }
}
