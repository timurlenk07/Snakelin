package com.snakelin.view

import com.snakelin.Styles
import com.snakelin.model.SnakelinModel
import com.snakelin.model.resetGame
import javafx.geometry.Pos
import tornadofx.*
import java.util.*

class HomeView : View("Snakelin Home") {
    override val root = borderpane {
        center = vbox(20, Pos.CENTER) {
            label("Snakelin")
            button("Play") {
                action {
                    this@HomeView.replaceWith(GameView::class)
                }
            }
            hbox(20, Pos.CENTER) {
                button("High scores") {
                    action {
                        println("Number of high scores: ${SnakelinModel.highScores.size}")
                        this@HomeView.replaceWith(HighScoresView::class)
                    }
                }
                button("Settings") {
                    action { println("$text pressed!") }
                }
            }
        }
        bottom = borderpane {
            left = label("by timurlenk") {
                addClass(Styles.smallLabel)
            }
            center = button("About") {
                action { println("Created by Márton Tim") }
                addClass(Styles.smallButton)
            }
            right = label("v.0.1.0 (alpha)") {
                addClass(Styles.smallLabel)
            }
        }
    }
}
