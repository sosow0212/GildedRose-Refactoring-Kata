package com.gildedrose.item.application

import com.gildedrose.item.application.strategy.AgedBrieUpdateStrategy
import com.gildedrose.item.application.strategy.BackstagePassesStrategy
import com.gildedrose.item.application.strategy.ConjuredStrategy
import com.gildedrose.item.application.strategy.ItemUpdateStrategy
import com.gildedrose.item.application.strategy.NormalUpdateStrategy
import com.gildedrose.item.application.strategy.SulfurasUpdateStrategy
import com.gildedrose.item.domain.Item
import com.gildedrose.item.domain.vo.ItemType

class ItemUpdater private constructor(
    private val itemUpdateStrategies: Map<ItemType, ItemUpdateStrategy>,
) {

    fun update(item: Item) {
        val itemType = ItemType.from(item.name)
        itemUpdateStrategies[itemType]
            ?.update(item)
    }

    companion object {
        fun createStrategies(): ItemUpdater {
            val updaterMap = mapOf(
                ItemType.NORMAL to NormalUpdateStrategy(),
                ItemType.AGED_BRIE to AgedBrieUpdateStrategy(),
                ItemType.SULFURAS to SulfurasUpdateStrategy(),
                ItemType.BACKSTAGE_PASSES to BackstagePassesStrategy(),
                ItemType.CONJURED to ConjuredStrategy()
            )
            return ItemUpdater(updaterMap)
        }
    }
}
