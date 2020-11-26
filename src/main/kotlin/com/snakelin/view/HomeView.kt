package com.snakelin.view

import com.snakelin.Styles
import javafx.geometry.Pos
import tornadofx.*

class HomeView : View("Snakelin Home") {
    override val root = borderpane {
        center = vbox(20, Pos.CENTER) {
            label("Snakelin") {
                style(append = true) {
                    fontSize = 36.px
                }
            }
            button("Play") {
                action {
                    this@HomeView.replaceWith(GameView::class)
                }
            }
            hbox(20, Pos.CENTER) {
                button("High scores") {
                    action {
                        this@HomeView.replaceWith(HighScoresView::class)
                    }
                }
                button("Settings") {
                    action { this@HomeView.replaceWith(OptionsView::class) }
                }
            }
        }
        bottom = borderpane {
            left = label("by timurlenk") {
                addClass(Styles.smallLabel)
            }
            center = button("About") {
                action { this@HomeView.replaceWith(AboutView::class) }
                addClass(Styles.smallButton)
            }
            right = label("v.0.1.0 (alpha)") {
                addClass(Styles.smallLabel)
            }
        }
    }
}
