package com.remonh87.comfortfood

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class OrderTest {

    private lateinit var sut: Order

    @Before
    fun setUp() {
        sut = Order()
    }

    @Test
    fun `HasOrderLines is true in case it has orders `() {
        val orderLine = OrderLine(Restaurant.LUIGI, "Test meal", 10.0)

        sut.add(orderLine)

        assertThat(sut.hasOrderLines).isTrue()
    }

    @Test
    fun `HasOrderLines is false in case it has no orders `() {
        assertThat(sut.hasOrderLines).isFalse()
    }
    
    @Test
    fun `It calculates price per restaurant correctly`() {

        val orderLine = OrderLine(Restaurant.LUIGI, "Test meal", 10.0)
        val orderLine2 = OrderLine(Restaurant.MIKE, "Delicious", 5.0)
        val orderLine3 = OrderLine(Restaurant.LUIGI, "Even better", 8.0)


        sut.add(orderLine)
        sut.add(orderLine2)
        sut.add(orderLine3)

        assertThat(sut.getPriceForRestaurant(Restaurant.LUIGI)).isEqualTo(18.0)

    }

    @Test
    fun `It prints calculates total correctly`() {

        val orderLine = OrderLine(Restaurant.LUIGI, "Test meal", 10.0)
        val orderLine2 = OrderLine(Restaurant.MIKE, "Delicious", 5.0)


        sut.add(orderLine)
        sut.add(orderLine2)

        assertThat(sut.totalPrice).isEqualTo(15.0)
    }


}