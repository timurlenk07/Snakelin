package com.snakelin.game

import com.snakelin.model.SnakelinModel
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

object GameEngine {
    val state: GameState get() = SnakelinModel.currentGame

    fun step(): PlayStatus = state.step()
    private fun GameState.step(): PlayStatus {
        // Move head
        val oldHead = player.head
        player.head += player.speed

        // Check if head overlaps an apple, if it does, add new segment, generate apple
        val consumed = player.head == apple.pos
        if (consumed) {
            player.consume(apple)
        }

        // Move last segment of snake to front
        player.body.add(0, oldHead)
        player.body.removeLast()

        if (consumed) {
            // Win: if snake covers whole map
            if (player.getHealth() == mapSize * mapSize) {
                status = PlayStatus.WIN
                return status
            }

            apple.pos = getRandomPos()
        }

        // Lose: if snake head is out of bounds
        // Lose: if snake head overlaps body
        val outOfBounds = player.head.x < 0 || player.head.y < 0 || player.head.x >= mapSize || player.head.y >= mapSize
        val eatsItself = player.body.contains(player.head)
        if (outOfBounds || eatsItself) {
            status = PlayStatus.GAME_OVER
            return status
        }

        return status
    }

    private fun getRandomPos(): Point {
        while (true) {
            val xnew = (0 until state.mapSize).random()
            val ynew = (0 until state.mapSize).random()
            val newPoint = Point(xnew, ynew)
            if (state.player.head != newPoint && !state.player.body.contains(newPoint)) {
                return newPoint
            }
        }
    }
}

fun GameState.drawOnCanvas(c: Canvas) {
    val gridSizeX = c.width / mapSize
    val gridSizeY = c.height / mapSize

    val gc = c.graphicsContext2D
    gc.fill = Color.BLANCHEDALMOND
    gc.fillRect(0.0, 0.0, c.width, c.height)

    // Apple
    gc.fill = Color.RED
    gc.fillOval(apple.pos.x * gridSizeX, apple.pos.y * gridSizeY, gridSizeX, gridSizeY)

    // Player
    gc.fill = Color.CHOCOLATE
    for (segment in player.body) {
        gc.fillOval(segment.x * gridSizeX, segment.y * gridSizeY, gridSizeX, gridSizeY)
    }
    gc.fill = Color.BLUE
    gc.fillOval(player.head.x * gridSizeX, player.head.y * gridSizeY, gridSizeX, gridSizeY)
}
