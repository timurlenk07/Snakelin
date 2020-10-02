package com.snakelin.view

import com.snakelin.model.SnakelinModel
import javafx.geometry.Pos
import tornadofx.*
import java.time.LocalDateTime
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class IntroView : View("Snakelin...") {
    override val root = vbox(alignment = Pos.CENTER) {
        label("Snakelin")
        /*imageview("/images/lena.png")
        imageview("/images/snake.ico")
        imageview("https://github.com/image.png")*/
    }

    override fun onDock() {
        super.onDock()
        thread {
            // TODO: Load options and highscores in controller
            val loadTime = measureTimeMillis {
                // Set saved resolution
                primaryStage.minWidth = SnakelinModel.resolution[0]
                primaryStage.minHeight = SnakelinModel.resolution[1]
            }
            if (loadTime < 1500) {
                Thread.sleep(1500 - loadTime)
            }

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
