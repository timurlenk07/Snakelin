package com.snakelin.view

import javafx.geometry.Pos
import tornadofx.*
import kotlin.concurrent.thread

class IntroView : View("Snakelin...") {
    override val root = vbox(alignment = Pos.CENTER) {
        label("Snakelin")
        imageview("/images/snake.ico")
    }

    init {
        primaryStage.minHeight = 360.0
        primaryStage.minWidth = 480.0
    }

    override fun onDock() {
        super.onDock()
        thread {
            Thread.sleep(2000)
            runLater {
                close()
                primaryStage.show()
            }
            /*println("Changing to HomeView...")
            this@IntroView.replaceWith(HomeView::class)
            println("Changed to HomeView.")*/
        }
    }
}
