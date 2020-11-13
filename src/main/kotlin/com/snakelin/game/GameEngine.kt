package com.snakelin.game

import com.snakelin.game.Direction.*
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import tornadofx.*
import kotlin.random.Random

enum class PLAY_STATUS {
    NOT_STARTED, PLAYING, GAME_OVER, WIN, PAUSED
}

class GameEngine {

    val mapSize = 8
    val player = Snake(Point2D(7.0))
    val apple = Apple(Point2D(0.0))
    var status = PLAY_STATUS.NOT_STARTED

    fun step() : PLAY_STATUS {
        // Move last segment of snake in front
        val oldHead = player.getHead()
        val newHead = when (player.speed) {
            NORTH -> oldHead.subtract(0.0, 1.0)
            SOUTH -> oldHead.add(0.0, 1.0)
            EAST -> oldHead.add(1.0, 0.0)
            WEST -> oldHead.subtract(1.0, 0.0)
        }
        player.segments.add(0, newHead)
        player.segments.removeLast()

        // Check if snake head is out of bounds (GAME OVER)
        if (player.getHead().x < 0 || player.getHead().y < 0 || player.getHead().x >= mapSize || player.getHead().y >= mapSize) {
            status = PLAY_STATUS.GAME_OVER
            return status
        }

        // Check if head overlaps an apple, if it does, add new segment, generate apple
        if (player.getHead().equals(apple.pos)) {
            player.consume(apple)
            val xnew = (0 until mapSize).random()
            val ynew = (0 until mapSize).random()
            apple.pos = Point2D(0.0).add(xnew.toDouble(), ynew.toDouble())
        }

        return status
    }
}

fun GameEngine.drawOnCanvas(c: Canvas) {
    val GRID_SIZE_X = c.width / mapSize
    val GRID_SIZE_Y = c.height / mapSize

    val gc = c.graphicsContext2D
    gc.fill = Color.AQUAMARINE
    gc.fillRect(0.0, 0.0, c.width, c.height)

    // Player
    gc.fill = Color.BLUE
    gc.fillOval(player.getHead().x * GRID_SIZE_X, player.getHead().y * GRID_SIZE_Y, GRID_SIZE_X, GRID_SIZE_Y)
    gc.fill = Color.GREEN
    for (segment in player.getBody()) {
        gc.fillOval(segment.x * GRID_SIZE_X, segment.y * GRID_SIZE_Y, GRID_SIZE_X, GRID_SIZE_Y)
    }

    // Apple
    gc.fill = Color.RED
    gc.fillOval(apple.pos.x * GRID_SIZE_X, apple.pos.y * GRID_SIZE_Y, GRID_SIZE_X, GRID_SIZE_Y)
}
