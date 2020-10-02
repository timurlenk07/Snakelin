package com.snakelin

import com.snakelin.view.HomeView
import com.snakelin.view.IntroView
import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.*

class SnakelinApp: App(HomeView::class, Styles::class) {
    init {
        reloadStylesheetsOnFocus()
    }

    override fun start(stage: Stage) {
        super.start(stage)
        find<IntroView>().openModal(stageStyle = StageStyle.UNDECORATED)
    }

    override fun shouldShowPrimaryStage() = false
}
