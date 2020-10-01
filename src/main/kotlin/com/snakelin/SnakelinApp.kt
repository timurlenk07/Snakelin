package com.snakelin

import com.snakelin.view.HomeView
import tornadofx.*

class SnakelinApp: App(HomeView::class, Styles::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}
