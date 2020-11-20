package com.snakelin.view.popup

import tornadofx.*

class AskForNamePopup : View("Enter your name") {
    var name = ""
    override val root = vbox {
        text("Enter your name: ")
        textfield {
            action {
                name = this.text
                this@AskForNamePopup.close()
            }
        }
        paddingAll = 15.0
    }
}