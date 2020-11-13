package com.snakelin.game

import kotlinx.serialization.Serializable

@Serializable
data class Point(val x: Int, val y: Int)

operator fun Point.plus(p: Point) = Point(x + p.x, y + p.y)
operator fun Point.minus(p: Point) = Point(x - p.x, y - p.y)

sealed class Entity(var pos: Point)

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

class Snake(head: Point, vararg body: Point) : Entity(head), Consumer {
    var head by this::pos
    val body = body.toMutableList()
    var speed = Direction.WEST

    override fun consume(that: Consumable) {
        when (that) {
            is Apple -> {
                body.add(body.last())
            }
            else -> println("Consumeable of class $")
        }
    }

    fun getHealth() = body.size + 1
}

class Apple(pos: Point) : Entity(pos), Consumable
