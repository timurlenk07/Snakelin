package com.snakelin.view

import com.snakelin.controller.GameController
import com.snakelin.game.drawOnCanvas
import com.snakelin.game.isFinished
import com.snakelin.game.isStarted
import com.snakelin.game.score
import com.snakelin.model.SnakelinModel
import com.snakelin.model.resetGame
import com.snakelin.model.saveGame
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyEvent
import javafx.scene.text.Text
import tornadofx.*

class GameView : View("Snakelin") {
    private val controller: GameController by inject()

    val gameCanvas = Canvas(360.0, 360.0)

    override val root = borderpane {
        top = hbox {
            paddingAll = 20.0
            text("Score: ")
            text(SnakelinModel.currentGame.score.toString()) {
                id = "score"
            }
        }
        center = stackpane {
            add(gameCanvas)

            text("Press Space to start!") {
                id = "gameMessage"
            }

            keyboard {
                addEventHandler(KeyEvent.KEY_PRESSED) {
                    controller.handleKeyEvent(it)
                }
            }
        }
    }

    val userText = root.lookup("#gameMessage") as Text
    val scoreText = root.lookup("#score") as Text

    override fun onDock() {
        if (SnakelinModel.currentGame.status.isFinished()) {
            SnakelinModel.resetGame()
            userText.text = "Press Space to start!"
        }
        SnakelinModel.currentGame.drawOnCanvas(gameCanvas)
    }

    override fun onUndock() {
        if (SnakelinModel.currentGame.status.isStarted() && !SnakelinModel.currentGame.status.isFinished()) {
            SnakelinModel.saveGame()
        }
    }
}
