package com.gildedrose.domain.vo

import com.gildedrose.item.domain.vo.ItemType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ItemTypeTest {

    @Test
    fun `이름과 같은 아이템 타입을 반환한다`() {
        // given
        val name = "Aged Brie"

        // when
        val result = ItemType.from(name)

        // then
        assertEquals(ItemType.AGED_BRIE, result)
    }

    @Test
    fun `이름과 같은 타입이 없다면 normal을 반환한다`() {
        // given
        val name = "sosow0212"

        // when
        val result = ItemType.from(name)

        // then
        assertEquals(ItemType.NORMAL, result)
    }
}
