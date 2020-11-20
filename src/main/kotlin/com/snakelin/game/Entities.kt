package com.snakelin.game

import kotlinx.serialization.Serializable

@Serializable
sealed class Entity


@Serializable
data class Snake(
        var head: Point,
        val body: MutableList<Point>,
        var speed: Direction = Direction.WEST,
) : Entity(), Consumer {

    override fun consume(that: Consumable) {
        when (that) {
            is Apple -> {
                body.add(body.last())
            }
            else -> println("Consumeable of class $")
        }
    }
}

fun Snake.getHealth() = body.size + 1


@Serializable
data class Apple(var pos: Point) : Entity(), Consumable
