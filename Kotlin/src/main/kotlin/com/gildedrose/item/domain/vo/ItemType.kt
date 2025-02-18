package com.gildedrose.item.domain.vo

enum class ItemType(
    val type: String
) {
    NORMAL("normal"),
    AGED_BRIE("Aged Brie"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert"),
    CONJURED("Conjured");

    companion object {
        fun from(name: String): ItemType {
            return entries.find { it.type == name }
                ?: NORMAL
        }
    }
}
