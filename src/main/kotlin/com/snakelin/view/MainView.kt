package com.snakelin.view

import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = hbox {
        button("Press me!")
        label("Waiting...")
        /*label(title) {
            addClass(Styles.heading)
        }*/
    }
}
