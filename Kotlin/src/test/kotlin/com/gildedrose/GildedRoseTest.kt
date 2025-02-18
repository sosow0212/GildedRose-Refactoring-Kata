package com.gildedrose

import com.gildedrose.item.domain.Item
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GildedRoseTest {

    @Test
    fun `Quality는 음수가 될 수 없다`() {
        // given
        val items = listOf(Item("name", 1, 0))
        val gildedRose = GildedRose(items)

        // when
        gildedRose.updateQuality()

        // then
        assertEquals(0, items[0].quality)
    }

    @Test
    fun `일반 아이템은 시간이 지나면 Quality가 감소하고 sellIn 또한 감소한다`() {
        // given
        val item = Item("name", 1, 1)
        val items = listOf(item)
        val gildedRose = GildedRose(items)

        // when
        gildedRose.updateQuality()

        // then
        assertAll(
            { assertEquals(0, items[0].quality) },
            { assertEquals(0, items[0].sellIn) }
        )
    }

    @Test
    fun `Aged Brie는 시간이 지날 수록 Quality가 올라간다`() {
        // given
        val item = Item("Aged Brie", 1, 1)
        val items = listOf(item)
        val gildedRose = GildedRose(items)

        // when
        gildedRose.updateQuality()

        // then
        assertEquals(2, item.quality)
    }

    @Test
    fun `Sulfuras, Hand of Ragnaros는 특징적으로 판매될 필요가 없고 Quality 값이 떨어지지 않는다`() {
        // given
        val item = Item("Sulfuras, Hand of Ragnaros", 1, 1)
        val items = listOf(item)
        val gildedRose = GildedRose(items)

        // when
        gildedRose.updateQuality()

        // then
        assertAll(
            { assertEquals(1, item.sellIn) },
            { assertEquals(1, item.quality) },
        )
    }

    @DisplayName("Backstage passes to a TAFKAL80ETC concert 테스트")
    @Nested
    inner class BackstagePassesTest {

        @Test
        fun `판매 기간이 10일 초과에서 Backstage Passes의 Quality가 1씩 증가한다`() {
            // given
            val item = Item("Backstage passes to a TAFKAL80ETC concert", 11, 1)
            val items = listOf(item)
            val gildedRose = GildedRose(items)

            // when
            gildedRose.updateQuality()

            // then
            assertAll(
                { assertEquals(10, item.sellIn) },
                { assertEquals(2, item.quality) },
            )
        }

        @Test
        fun `판매 기간이 10일 이하부터 5일 초과까지는 Backstage Passes의 Quality가 2씩 증가한다`() {
            // given
            val item = Item("Backstage passes to a TAFKAL80ETC concert", 6, 1)
            val items = listOf(item)
            val gildedRose = GildedRose(items)

            // when
            gildedRose.updateQuality()

            // then
            assertAll(
                { assertEquals(5, item.sellIn) },
                { assertEquals(3, item.quality) },
            )
        }

        @Test
        fun `판매 기간이 5일 이하부터는 Backstage Passes의 Quality가 3씩 증가한다`() {
            // given
            val item = Item("Backstage passes to a TAFKAL80ETC concert", 3, 1)
            val items = listOf(item)
            val gildedRose = GildedRose(items)

            // when
            gildedRose.updateQuality()

            // then
            assertAll(
                { assertEquals(2, item.sellIn) },
                { assertEquals(4, item.quality) },
            )
        }

        @Test
        fun `판매일이 지난 후 Backstage Passes의 Quality는 0으로 떨어진다`() {
            // given
            val item = Item("Backstage passes to a TAFKAL80ETC concert", 0, 1)
            val items = listOf(item)
            val gildedRose = GildedRose(items)

            // when
            gildedRose.updateQuality()

            // then
            assertAll(
                { assertEquals(-1, item.sellIn) },
                { assertEquals(0, item.quality) },
            )
        }
    }

    @Test
    fun `Conjured 아이템은 일반 아이템의 2배 속도로 Quality가 떨어진다`() {
        // given
        val item = Item("Conjured", 10, 10)
        val items = listOf(item)
        val gildedRose = GildedRose(items)

        // when
        gildedRose.updateQuality()

        // then
        assertAll(
            { assertEquals(9, item.sellIn) },
            { assertEquals(8, item.quality) },
        )
    }
}
