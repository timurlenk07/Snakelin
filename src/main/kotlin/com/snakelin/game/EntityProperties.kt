package com.snakelin.game

interface Consumable {
    fun whenConsumed() = Unit
}

interface Consumer {
    fun consume(that: Consumable)
}
