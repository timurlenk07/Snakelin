package com.snakelin.view

import com.snakelin.model.SnakelinModel
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import tornadofx.*

class GameView : View("Snakelin") {
    val gameThread: Thread
    val gameCanvas = Canvas(primaryStage.width, primaryStage.height)

    override val root = stackpane {
        this += gameCanvas
        text("Press Space to start!") {
            id = "gameMessage"
        }

        keyboard {
            addEventHandler(KeyEvent.KEY_PRESSED) {
                if (it.code == KeyCode.ESCAPE) {
                    println("You've pressed ESCAPE man! THat's cool!")
                    this@GameView.replaceWith(PauseView::class)
                }
            }
        }
    }

    init {
        gameThread = Thread {
            // TODO: frquently call drawing on canvas
            while (true) {
                SnakelinModel.currentGame.drawOnCanvas(gameCanvas)
                Thread.sleep(30)
            }
        }
    }

    fun start() {
        gameThread.start()
    }

    fun stop() {
        gameThread.interrupt()
    }
}
