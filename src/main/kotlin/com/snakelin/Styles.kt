package com.snakelin

import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val smallLabel by cssclass()
        val smallButton by cssclass()
        val defaultFont = loadFont("/fonts/Druk Wide Bold.ttf", 16)!!
    }

    init {
        s(button, label) {
            padding = box(10.px)
            fontFamily = defaultFont.family //"Courier New", Courier, monospace
            fontSize = 24.px
            textFill = Color.DARKGREEN
        }
        smallLabel {
            padding = box(10.px)
            fontFamily = defaultFont.family //"Courier New", Courier, monospace
            fontSize = 14.px
        }
        smallButton {
            padding = box(0.px)
            fontFamily = defaultFont.family //"Courier New", Courier, monospace
            fontSize = 14.px
        }
        /*label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }*/
        //println(Font.getFamilies())
    }
}