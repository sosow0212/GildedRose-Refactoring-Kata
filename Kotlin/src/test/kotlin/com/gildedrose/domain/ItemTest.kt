package com.gildedrose.domain

import com.gildedrose.item.domain.Item
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ItemTest {

    @DisplayName("Item 생성 테스트")
    @Nested
    inner class CreateItemTest {

        @ValueSource(ints = [-1, 51])
        @ParameterizedTest
        fun `quality는 0 ~ 50의 값을 가지게 생성해야한다`(quality: Int) {
            // when & then
            assertThrows(IllegalArgumentException::class.java) {
                Item("name", 10, quality)
            }
        }

        @Test
        fun `sellIn을 음수로 생성할 수 없다`() {
            // given
            val sellIn = -1

            // when & then
            assertThrows(IllegalArgumentException::class.java) {
                Item("name", sellIn, 1)
            }
        }
    }
}
