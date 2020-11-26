package com.snakelin.view.popup

import com.snakelin.Styles
import tornadofx.*

class AskForNamePopup(val minLength: Int) : View("Enter your name") {
    var name = ""
    override val root = vbox {
        label("Enter your name (min. 3 characters): ") { addClass(Styles.smallLabel) }
        textfield {
            action {
                name = this.text
                if (name.length >= minLength) {
                    this@AskForNamePopup.close()
                }
            }
        }
        paddingAll = 15.0
    }
}