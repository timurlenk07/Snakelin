package com.snakelin.view

import com.snakelin.Styles
import javafx.geometry.Pos
import tornadofx.*

class HomeView : View("My View") {
    override val root = borderpane {
        center = vbox(20, Pos.CENTER) {
            label("Snakelin")
            button("Play") {
                action { println("$text pressed!") }
            }
            hbox(20, Pos.CENTER) {
                button("High scores") {
                    action { println("$text pressed!") }
                }
                button("Settings") {
                    action { println("$text pressed!") }
                }
            }
        }
        bottom = borderpane {
            left = label("by timurlenk")
            right = label("v.0.1.0 (alpha)")
            children.style { Styles.smallLabel }
        }

        //children.filter { it is Button }.addClass(Styles.snakelinButton) }
    }

    init {
        primaryStage.minHeight = 360.0
        primaryStage.minWidth = 480.0
    }
}
