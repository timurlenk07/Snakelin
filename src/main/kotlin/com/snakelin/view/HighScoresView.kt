package com.snakelin.view

import com.snakelin.Styles
import com.snakelin.model.Score
import com.snakelin.model.SnakelinModel
import javafx.geometry.Pos
import tornadofx.*


class HighScoresView : View("Snakelin Home") {
    override val root = borderpane {
        top = label("High scores")
        center = tableview(SnakelinModel.highScores.asObservable()) {
            readonlyColumn("Name", Score::name)
            readonlyColumn("Score", Score::score)
            columnResizePolicy = SmartResize.POLICY
        }
        bottom = hbox(alignment = Pos.CENTER_RIGHT) {
            button("Back") {
                action { this@HighScoresView.replaceWith(HomeView::class) }
            }
        }
    }
}