package com.snakelin.view

import com.snakelin.game.*
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
    private val gameCanvas = Canvas(400.0, 400.0)
    private val userText: Text

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
                        when (SnakelinModel.currentGame.status)  {
                            PLAY_STATUS.GAME_OVER, PLAY_STATUS.WIN -> {
                                pause()
                                this@GameView.replaceWith(HomeView::class)
                            }
                            else -> {
                                SnakelinModel.currentGame.status = PLAY_STATUS.PAUSED
                                pause()
                                this@GameView.replaceWith(PauseView::class)
                            }
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
        if (SnakelinModel.currentGame.status.isOneOf(PLAY_STATUS.GAME_OVER, PLAY_STATUS.WIN)) {
            SnakelinModel.resetGame()
            userText.text = "Press Space to start!"
        }
        SnakelinModel.currentGame.drawOnCanvas(gameCanvas)
    }

    override fun onUndock() {
        if (SnakelinModel.currentGame.status.isOneOf(PLAY_STATUS.PLAYING, PLAY_STATUS.PAUSED)) {
            Unit // Save  game state
            println("We want to save the game state! DO IT!")
        }
    }

    private fun start() {
        if (gameLoop != null) return

        userText.isVisible = false
        SnakelinModel.currentGame.status = PLAY_STATUS.PLAYING

        gameLoop = GlobalScope.launch {
            while (SnakelinModel.currentGame.status == PLAY_STATUS.PLAYING) {
                delay(400)
                val newState = SnakelinModel.currentGame.step()
                SnakelinModel.currentGame.drawOnCanvas(gameCanvas)
                when (newState) {
                    PLAY_STATUS.GAME_OVER -> {
                        userText.text = "Game Over!"
                        userText.isVisible = true
                    }
                    PLAY_STATUS.WIN -> {
                        userText.text = "You won!"
                        userText.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun pause() {
        gameLoop?.cancel()
        runBlocking {
            gameLoop?.join()
        }
        userText.isVisible = true
        gameLoop = null
    }
}
