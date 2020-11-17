package com.snakelin.game

import com.snakelin.game.Direction.*
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

enum class PLAY_STATUS {
    NOT_STARTED, PLAYING, GAME_OVER, WIN, PAUSED
}

class GameEngine {

    val mapSize = 3
    val player = Snake(Point(2, 2), Point(2, 1), Point(2, 0))
    val apple = Apple(Point(1, 1))
    var status = PLAY_STATUS.NOT_STARTED
    val score: Int
        get() {
            return player.getHealth()
        }

    fun step() : PLAY_STATUS {
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
                status = PLAY_STATUS.WIN
                return status
            }

            apple.pos = getRandomPos()
        }

        // Lose: if snake head is out of bounds
        // Lose: if snake head overlaps body
        val outOfBounds = player.head.x < 0 || player.head.y < 0 || player.head.x >= mapSize || player.head.y >= mapSize
        val eatsItself = player.body.contains(player.head)
        if (outOfBounds || eatsItself) {
            status = PLAY_STATUS.GAME_OVER
            return status
        }

        return status
    }

    private fun getRandomPos() : Point {
        while (true) {
            val xnew = (0 until mapSize).random()
            val ynew = (0 until mapSize).random()
            val newPoint = Point(xnew, ynew)
            if (player.head != newPoint && !player.body.contains(newPoint)) {
                return newPoint
            }
        }
    }
}

fun GameEngine.drawOnCanvas(c: Canvas) {
    val GRID_SIZE_X = c.width / mapSize
    val GRID_SIZE_Y = c.height / mapSize

    val gc = c.graphicsContext2D
    gc.fill = Color.AQUAMARINE
    gc.fillRect(0.0, 0.0, c.width, c.height)

    // Apple
    gc.fill = Color.RED
    gc.fillOval(apple.pos.x * GRID_SIZE_X, apple.pos.y * GRID_SIZE_Y, GRID_SIZE_X, GRID_SIZE_Y)

    // Player
    gc.fill = Color.GREEN
    for (segment in player.body) {
        gc.fillOval(segment.x * GRID_SIZE_X, segment.y * GRID_SIZE_Y, GRID_SIZE_X, GRID_SIZE_Y)
    }
    gc.fill = Color.BLUE
    gc.fillOval(player.head.x * GRID_SIZE_X, player.head.y * GRID_SIZE_Y, GRID_SIZE_X, GRID_SIZE_Y)
}

fun PLAY_STATUS.isOneOf(vararg dirs: PLAY_STATUS) : Boolean {
    if (dirs.contains(this)) {
        return true
    }
    return false
}

operator fun Point.plus(dir: Direction) : Point {
    return when (dir) {
        NORTH -> this - Point(0, 1)
        SOUTH -> this + Point(0, 1)
        EAST -> this + Point(1, 0)
        WEST -> this - Point(1, 0)
    }
}
