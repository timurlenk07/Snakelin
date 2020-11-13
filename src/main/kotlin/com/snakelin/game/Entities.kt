package com.snakelin.game

import javafx.geometry.Point2D
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

sealed class Entity(var pos: Point2D)

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

class Snake(pos: Point2D) : Entity(pos), Consumer {

    var speed = Direction.NORTH
    val segments = mutableListOf(pos, pos, pos)

    override fun consume(that: Consumable) {
        when (that) {
            is Apple -> {
                segments.add(segments.last())
            }
            else -> println("Consumeable of class $")
        }
    }

    fun getHealth() = segments.size
    fun getHead() = segments[0]
    fun getBody() = segments.subList(1, segments.size)
}

// Note: pos for circular objects means centerpoint
class Apple(pos: Point2D) : Entity(pos), Consumable
