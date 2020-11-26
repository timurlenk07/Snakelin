package com.snakelin.controller

import com.snakelin.game.*
import com.snakelin.model.Score
import com.snakelin.model.SnakelinModel
import com.snakelin.model.saveHighScores
import com.snakelin.view.GameView
import com.snakelin.view.HomeView
import com.snakelin.view.PauseView
import com.snakelin.view.popup.AskForNamePopup
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.stage.Modality
import kotlinx.coroutines.*
import kotlinx.coroutines.javafx.JavaFx
import tornadofx.*

class GameController : Controller() {
    private val view: GameView by inject()

    private var gameLoop: Job? = null

    fun handleKeyEvent(key: KeyEvent) {
        when (key.code) {
            KeyCode.ESCAPE -> {
                if (SnakelinModel.currentGame.status.isFinished()) {
                    pause()
                    view.replaceWith(HomeView::class)
                } else {
                    pause()
                    SnakelinModel.currentGame.status = PlayStatus.PAUSED
                    view.replaceWith(PauseView::class)
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

    private fun start() {
        if (gameLoop != null) return

        view.userText.isVisible = false
        SnakelinModel.currentGame.status = PlayStatus.PLAYING

        gameLoop = GlobalScope.launch {
            while (SnakelinModel.currentGame.status == PlayStatus.PLAYING) {
                delay(SnakelinModel.options.gameSpeed.toLong())
                val newState = GameEngine.step()
                SnakelinModel.currentGame.drawOnCanvas(view.gameCanvas)
                withContext(Dispatchers.JavaFx) {
                    view.scoreText.text = SnakelinModel.currentGame.score.toString()
                    when (newState) {
                        PlayStatus.GAME_OVER -> {
                            view.userText.text = "Game Over!"
                        }
                        PlayStatus.WIN -> {
                            view.userText.text = "You won!"
                        }
                        else -> Unit
                    }
                }
                if (SnakelinModel.currentGame.status.isFinished()) {
                    val name = withContext(Dispatchers.JavaFx) {
                        view.userText.isVisible = true
                        val popup = AskForNamePopup(3)
                        popup.openWindow(modality = Modality.WINDOW_MODAL, block = true)?.centerOnScreen()
                        popup.name
                    }
                    SnakelinModel.highScores.add(Score(SnakelinModel.currentGame.score, name))
                    SnakelinModel.saveHighScores() // TODO: find a better place for this
                    withContext(Dispatchers.JavaFx) {
                        view.replaceWith(HomeView::class)
                    }
                }
            }
            gameLoop = null
        }
    }

    private fun pause() {
        gameLoop?.cancel()
        runBlocking {
            gameLoop?.join()
        }
        view.userText.isVisible = true
        gameLoop = null
    }
}
