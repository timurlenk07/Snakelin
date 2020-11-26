package com.snakelin.view

import com.snakelin.Styles
import tornadofx.*

class AboutView : View("Snakelin Home") {
    override val root = borderpane {
        top = stackpane { label("About Snakelin") }
        center = vbox(spacing = 10) {
            label("Created by Márton Tim")
            label("Homework for \"Kotlin-based software development\" @ BME") {
                isWrapText = true
            }
        }
        bottom = borderpane {
            paddingAll = 15
            left = label("Contact: tmarcikka@gmail.com") {
                addClass(Styles.smallLabel)
            }
            right = button("Back") { action { this@AboutView.replaceWith(HomeView::class) } }
        }
    }
}