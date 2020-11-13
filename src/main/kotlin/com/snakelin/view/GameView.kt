package com.snakelin.view

import com.snakelin.game.Direction
import com.snakelin.game.GameEngine
import com.snakelin.game.PLAY_STATUS
import com.snakelin.game.drawOnCanvas
import com.snakelin.model.SnakelinModel
import com.snakelin.model.resetGame
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.text.Text
import tornadofx.*
import kotlinx.coroutines.*

class GameView : View("Snakelin") {
    private var gameLoop: Job? = null
    val gameCanvas = Canvas(400.0, 400.0)
    val userText: Text

    override val root = stackpane {
        /*gameCanvas.width = this.width
        gameCanvas.height = this.height*/
        this += gameCanvas

        text("Press Space to start!") {
            id = "gameMessage"
        }

        keyboard {
            addEventHandler(KeyEvent.KEY_PRESSED) {
                when (it.code) {
                    KeyCode.ESCAPE -> {
                        if (SnakelinModel.currentGame.status != PLAY_STATUS.GAME_OVER) {
                            SnakelinModel.currentGame.status = PLAY_STATUS.PAUSED
                            pause()
                            this@GameView.replaceWith(PauseView::class)
                        } else {
                            pause()
                            this@GameView.replaceWith(HomeView::class)
                        }
                        println("Status is " + SnakelinModel.currentGame.status)
                    }
                    KeyCode.SPACE -> start()
                    KeyCode.DOWN -> SnakelinModel.currentGame.player.speed = Direction.SOUTH
                    KeyCode.UP -> SnakelinModel.currentGame.player.speed = Direction.NORTH
                    KeyCode.LEFT -> SnakelinModel.currentGame.player.speed = Direction.WEST
                    KeyCode.RIGHT -> SnakelinModel.currentGame.player.speed = Direction.EAST
                    else -> Unit
                }
            }
        }
    }

    init {
        userText = root.lookup("#gameMessage") as Text
    }

    override fun onDock() {
        super.onDock()
        println("GameView onDock")
        if (SnakelinModel.currentGame.status == PLAY_STATUS.GAME_OVER) {
            SnakelinModel.resetGame()
            userText.text = "Press Space to start!"
        }
        SnakelinModel.currentGame.drawOnCanvas(gameCanvas)
    }

    fun start() {
        if (gameLoop != null) return

        gameLoop = GlobalScope.launch {
            userText.isVisible = false
            SnakelinModel.currentGame.status = PLAY_STATUS.PLAYING
            while (SnakelinModel.currentGame.status == PLAY_STATUS.PLAYING) {
                SnakelinModel.currentGame.step()
                if (SnakelinModel.currentGame.status == PLAY_STATUS.GAME_OVER) {
                    userText.text = "Game Over!"
                    userText.isVisible = true
                    break
                }
                SnakelinModel.currentGame.drawOnCanvas(gameCanvas)
                delay(500)
            }
        }
    }

    fun pause() {
        gameLoop?.cancel()
        runBlocking {
            gameLoop?.join()
        }
        userText.isVisible = true
        gameLoop = null
    }
}
