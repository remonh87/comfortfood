package com.remonh87.comfortfood

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class OrderTest {

    lateinit var sut: Order

    @Before
    fun setUp() {
        sut = Order()
    }

    @Test
    fun `It prints single orderline correctly`() {
        val orderLine = OrderLine(Restaurant.LUIGI, "Test meal", 10.0)

        sut.add(orderLine)

        assertThat(sut.print()).isEqualTo("LUIGI \t $10.0\n- Test meal")
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
    fun `It prints multi orderline correctly`() {

        val orderLine = OrderLine(Restaurant.LUIGI, "Test meal", 10.0)
        val orderLine2 = OrderLine(Restaurant.LUIGI, "Delicious", 5.0)

        sut.add(orderLine)
        sut.add(orderLine2)

        assertThat(sut.print()).isEqualTo("LUIGI \t $15.0\n- Test meal\n- Delicious")

    }


    @Test
    fun `It prints order of multiple restaurants correctly`() {

        val orderLine = OrderLine(Restaurant.LUIGI, "Test meal", 10.0)
        val orderLine2 = OrderLine(Restaurant.MIKE, "Delicious", 5.0)
        val orderLine3 = OrderLine(Restaurant.LUIGI, "Even better", 10.0)


        sut.add(orderLine)
        sut.add(orderLine2)
        sut.add(orderLine3)

        val expectedPrint = "LUIGI \t $20.0\n- Test meal\n- Even better\n\nMIKE \t \$5.0\n" +
                "- Delicious"
        assertThat(sut.print()).isEqualTo(expectedPrint)

    }


}