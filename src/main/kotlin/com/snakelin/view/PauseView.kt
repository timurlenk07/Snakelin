package com.snakelin.view

import javafx.geometry.Pos
import tornadofx.*

class PauseView : View("My View") {
    override val root = vbox(10, alignment = Pos.CENTER) {
        button("Continue") {
            action {
                this@PauseView.replaceWith(GameView::class)
            }
        }
        button("Return to menu") {
            action {
                this@PauseView.replaceWith(HomeView::class)
            }
        }
    }
}
