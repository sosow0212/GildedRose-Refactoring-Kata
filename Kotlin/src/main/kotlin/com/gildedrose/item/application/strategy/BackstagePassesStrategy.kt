package com.gildedrose.item.application.strategy

import com.gildedrose.item.domain.Item
import com.gildedrose.item.domain.vo.ItemType

class BackstagePassesStrategy : ItemUpdateStrategy {

    override fun getSupportedItemType(): ItemType {
        return ItemType.BACKSTAGE_PASSES
    }

    override fun update(item: Item) {
        val updatedSellIn = item.sellIn - 1
        val updatedQuality = when {
            item.sellIn > 10 -> item.quality + 1
            item.sellIn > 5 -> item.quality + 2
            item.sellIn > 0 -> item.quality + 3
            else -> 0
        }

        item.update(updatedSellIn, updatedQuality)
    }
}
