package com.snakelin.game

import javafx.geometry.Point2D
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import tornadofx.*


class GameState {
    val player = Player(Point2D(240.0))
    val apple = Apple(Point2D(100.0))

    fun drawOnCanvas(c: Canvas) {
        c.graphicsContext2D.fill = Color.AQUAMARINE
        c.graphicsContext2D.fillRect(0.0, 0.0, c.width, c.height)
        player.draw(c)
        apple.draw(c)
    }
}