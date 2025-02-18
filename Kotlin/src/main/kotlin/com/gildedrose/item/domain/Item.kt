package com.gildedrose.item.domain

class Item(
    val name: String,
    var sellIn: Int,
    var quality: Int
) {

    init {
        require(quality in MINIMUM_QUALITY..MAXIMUM_QUALITY) { "quality는 0~50의 값을 가져야합니다." }
        require(sellIn >= MINIMUM_SELL_IN) { "sellIn은 음수가 될 수 없습니다." }
    }

    fun update(updatedSellIn: Int, updatedQuality: Int) {
        this.sellIn = updatedSellIn
        this.quality = ensureValidQuality(updatedQuality)
    }

    private fun ensureValidQuality(quality: Int): Int {
        return when {
            quality < MINIMUM_QUALITY -> MINIMUM_QUALITY
            quality > MAXIMUM_QUALITY -> MAXIMUM_QUALITY
            else -> quality
        }
    }

    companion object {
        private const val MINIMUM_QUALITY = 0
        private const val MAXIMUM_QUALITY = 50
        private const val MINIMUM_SELL_IN = 0
    }
}
