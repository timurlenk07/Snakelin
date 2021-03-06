package com.snakelin

import com.snakelin.model.SnakelinModel
import com.snakelin.model.loadGame
import com.snakelin.model.loadHighScores
import com.snakelin.model.loadOptions
import com.snakelin.view.HomeView
import javafx.stage.Stage
import tornadofx.*


class SnakelinApp : App(HomeView::class, Styles::class) {
    override fun start(stage: Stage) {
        super.start(stage)

        SnakelinModel.loadOptions()
        SnakelinModel.loadHighScores()
        SnakelinModel.loadGame()

        // Set saved resolution
        stage.minWidth = SnakelinModel.resolution[0]
        stage.minHeight = SnakelinModel.resolution[1]
        println("Resolution set to ${SnakelinModel.resolution[0]}")

        stage.isResizable = false
        stage.centerOnScreen()
    }
}

fun main() {
    launch<SnakelinApp>()
}
