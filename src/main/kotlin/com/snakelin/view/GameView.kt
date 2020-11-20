package com.snakelin.view

import com.snakelin.game.*
import com.snakelin.model.*
import com.snakelin.view.popup.AskForNamePopup
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.text.Text
import javafx.stage.Modality
import tornadofx.*
import kotlinx.coroutines.*
import kotlinx.coroutines.javafx.JavaFx

class GameView : View("Snakelin") {
    private var gameLoop: Job? = null
    private val gameCanvas = Canvas(360.0, 360.0)
    private val userText: Text
    private val scoreText: Text

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
                    when (it.code) {
                        KeyCode.ESCAPE -> {
                            when (SnakelinModel.currentGame.status) {
                                PLAY_STATUS.GAME_OVER, PLAY_STATUS.WIN -> {
                                    pause()
                                    this@GameView.replaceWith(HomeView::class)
                                }
                                else -> {
                                    pause()
                                    SnakelinModel.currentGame.status = PLAY_STATUS.PAUSED
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
    }

    init {
        userText = root.lookup("#gameMessage") as Text
        scoreText = root.lookup("#score") as Text
    }

    override fun onDock() {
        SnakelinModel.loadGame()
        if (SnakelinModel.currentGame.status.isOneOf(PLAY_STATUS.GAME_OVER, PLAY_STATUS.WIN)) {
            SnakelinModel.resetGame()
            userText.text = "Press Space to start!"
        }
        SnakelinModel.currentGame.drawOnCanvas(gameCanvas)
    }

    override fun onUndock() {
        if (SnakelinModel.currentGame.status.isOneOf(PLAY_STATUS.PLAYING, PLAY_STATUS.PAUSED)) {
            SnakelinModel.saveGame()
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
                scoreText.text = SnakelinModel.currentGame.score.toString()
                when (newState) {
                    PLAY_STATUS.GAME_OVER -> {
                        userText.text = "Game Over!"
                        userText.isVisible = true
                    }
                    PLAY_STATUS.WIN -> {
                        userText.text = "You won!"
                    }
                    else -> Unit
                }
                if (SnakelinModel.currentGame.status.isOneOf(PLAY_STATUS.GAME_OVER, PLAY_STATUS.WIN)) {
                    userText.isVisible = true
                    var name = ""
                    withContext(Dispatchers.JavaFx) {
                        val popup = AskForNamePopup()
                        popup.openWindow(modality = Modality.WINDOW_MODAL, block = true)
                        name = popup.name
                    }
                    SnakelinModel.highScores.add(Score(SnakelinModel.currentGame.score, name))
                    SnakelinModel.saveHighScores() // TODO: find a better place for this
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
