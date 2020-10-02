package com.snakelin

import javafx.scene.text.Font
import tornadofx.*
import java.util.concurrent.ConcurrentSkipListSet

class Styles : Stylesheet() {
    companion object {
        val smallLabel by cssclass()
        val smallButton by cssclass()
        val defaultFont = loadFont("/fonts/Snake V2.ttf", 16)!!
    }

    init {
        s(button, label) {
            padding = box(10.px)
            fontFamily = defaultFont.family //"Courier New", Courier, monospace
            fontSize = 46.px
        }
        smallLabel {
            padding = box(10.px)
            fontFamily = defaultFont.family //"Courier New", Courier, monospace
            fontSize = 24.px
        }
        smallButton {
            padding = box(0.px)
            fontFamily = defaultFont.family //"Courier New", Courier, monospace
            fontSize = 24.px
        }
        /*label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }*/
        //println(Font.getFamilies())
    }
}