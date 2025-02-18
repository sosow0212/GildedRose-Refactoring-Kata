package com.gildedrose.item.application.strategy

import com.gildedrose.item.domain.Item
import com.gildedrose.item.domain.vo.ItemType

class SulfurasUpdateStrategy : ItemUpdateStrategy {

    override fun getSupportedItemType(): ItemType {
        return ItemType.SULFURAS
    }

    override fun update(item: Item) {
        val sellIn = item.sellIn
        val quality = item.quality

        item.update(
            updatedSellIn = sellIn,
            updatedQuality = quality
        )
    }
}
