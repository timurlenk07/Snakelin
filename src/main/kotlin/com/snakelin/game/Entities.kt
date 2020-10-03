package com.snakelin.game

import javafx.geometry.Point2D
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import tornadofx.*

sealed class Entity(var pos: Point2D) : Drawable

class Player(pos: Point2D) : Entity(pos), Steppable, Consumer {
    companion object {
        const val MAX_SPEED = 10.0
        const val SEGMENT_SIZE = 20.0
    }

    var speed = Point2D(0.0)
    var length = 3

    override fun draw(c: Canvas) {
        c.graphicsContext2D.fill = Color.GREEN
        c.graphicsContext2D.fillOval(pos.x - SEGMENT_SIZE /2, pos.y - SEGMENT_SIZE /2, SEGMENT_SIZE, SEGMENT_SIZE)
    }

    override fun step(timescale: Double) {
        pos += speed * timescale
    }

    override fun consume(that: Consumeable) {
        when (that) {
            is Apple -> {
                length += 1
            }
            else -> println("Consumeable of class $")
        }
    }
}

// Note: pos for circular objects means centerpoint
class Apple(pos: Point2D) : Entity(pos), Consumeable {
    companion object {
        const val APPLE_SIZE = 20.0
    }

    override fun draw(c: Canvas) {
        c.graphicsContext2D.fill = Color.RED
        c.graphicsContext2D.fillOval(pos.x - APPLE_SIZE/2, pos.y - APPLE_SIZE/2, APPLE_SIZE, APPLE_SIZE)
    }
}
