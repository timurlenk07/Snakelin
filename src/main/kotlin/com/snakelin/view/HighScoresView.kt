package com.snakelin.view

import com.snakelin.Styles
import com.snakelin.model.Score
import com.snakelin.model.SnakelinModel
import javafx.geometry.Pos
import javafx.scene.layout.Background
import javafx.scene.layout.Border
import tornadofx.*


class HighScoresView : View("Snakelin Home") {
    override val root = borderpane {
        top = stackpane { label("High scores") {
            style(append = true) { fontSize = 36.px }
        } }
        center = stackpane {
            paddingAll = 5
            tableview(SnakelinModel.highScores.sortedDescending().asObservable()) {
                readonlyColumn("Name", Score::name) {
                    prefWidth = 350.0
                }
                readonlyColumn("Score", Score::score)
                columnResizePolicy = SmartResize.POLICY
                border = Border.EMPTY
            }
        }
        bottom = hbox(alignment = Pos.CENTER_RIGHT) {
            button("Back") {
                action { this@HighScoresView.replaceWith(HomeView::class) }
            }
        }
    }
}