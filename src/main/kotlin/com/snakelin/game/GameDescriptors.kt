package com.snakelin.game

import kotlinx.serialization.Serializable

@Serializable
data class Point(val x: Int, val y: Int)

operator fun Point.plus(p: Point) = Point(x + p.x, y + p.y)
operator fun Point.minus(p: Point) = Point(x - p.x, y - p.y)


enum class Direction {
    NORTH, SOUTH, WEST, EAST
}


enum class PLAY_STATUS {
    NOT_STARTED, PLAYING, GAME_OVER, WIN, PAUSED
}

fun PLAY_STATUS.isOneOf(vararg dirs: PLAY_STATUS): Boolean {
    if (dirs.contains(this)) {
        return true
    }
    return false
}

operator fun Point.plus(dir: Direction): Point {
    return when (dir) {
        Direction.NORTH -> this - Point(0, 1)
        Direction.SOUTH -> this + Point(0, 1)
        Direction.EAST -> this + Point(1, 0)
        Direction.WEST -> this - Point(1, 0)
    }
}