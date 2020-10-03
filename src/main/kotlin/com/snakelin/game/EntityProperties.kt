package com.snakelin.game

import javafx.scene.canvas.Canvas

interface Drawable {
    fun draw(c: Canvas)
}

interface Steppable {
    fun step(timescale: Double = 1.0)
}

interface Consumeable

interface Consumer {
    fun consume(that: Consumeable)
}
