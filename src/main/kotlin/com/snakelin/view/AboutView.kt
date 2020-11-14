package com.snakelin.view

import tornadofx.*

class AboutView : View("Snakelin Home") {
    override val root = borderpane {
        top = label("High scores")
        center = vbox(spacing = 10) {
            label("Created by Márton Tim")
            label("Homework for \"Kotlin-based software development\" @ BME")
        }
        bottom = borderpane {
            left = label("Contact: tmarcikka@gmail.com")
            right = button("Back") { action { this@AboutView.replaceWith(HomeView::class) } }
        }
    }
}