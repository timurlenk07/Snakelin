package com.snakelin.view

import com.snakelin.model.SnakelinModel
import com.snakelin.model.saveOptions
import javafx.geometry.Pos
import javafx.scene.control.Slider
import tornadofx.*
import kotlin.math.roundToInt

class OptionsView : View("Snakelin Options") {
    override val root = borderpane {
        top = hbox(alignment = Pos.TOP_CENTER) { label("Options") }
        center = form {
            fieldset {
                paddingAll = 10
                field("Game speed", forceLabelIndent = true) {
                    slider(100, 600, SnakelinModel.options.gameSpeed) {
                        id = "gameSpeedSlider"
                        isSnapToTicks = true
                        isShowTickMarks = true
                        isShowTickLabels = true
                    }
                }
                field("Map size", forceLabelIndent = true) {
                    slider(3, 25, SnakelinModel.options.mapSize) {
                        id = "mapSizeSlider"
                        isSnapToTicks = true
                        isShowTickMarks = true
                        isShowTickLabels = true
                    }
                }
            }
        }
        bottom = borderpane {
            paddingAll = 15
            right = button("Save & Back") {
                action {
                    SnakelinModel.options.gameSpeed = gameSpeedSlider.value.roundToInt()
                    SnakelinModel.options.mapSize = mapSizeSlider.value.roundToInt()
                    println("Settings are: ${SnakelinModel.options}")
                    SnakelinModel.saveOptions()
                    this@OptionsView.replaceWith(HomeView::class)
                }
            }
        }
    }
    private val gameSpeedSlider: Slider = root.lookup("#gameSpeedSlider") as Slider
    private val mapSizeSlider: Slider = root.lookup("#mapSizeSlider") as Slider
}