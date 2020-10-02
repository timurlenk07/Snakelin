package com.snakelin.view

import com.snakelin.Styles
import javafx.geometry.Pos
import tornadofx.*

class HomeView : View("Snakelin") {
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

    init {
        primaryStage.minHeight = 360.0
        primaryStage.minWidth = 480.0
    }
}
