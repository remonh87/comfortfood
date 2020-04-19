package com.remonh87.pizza.businesslogic

import com.google.common.truth.Truth.assertThat
import com.remonh87.pizza.businesslogic.Pizza
import com.remonh87.pizza.businesslogic.PizzaOrder
import org.junit.Before
import org.junit.Test


class PizzaOrderTest {

    lateinit var sut: PizzaOrder
    private val pizzaMargherita =
        Pizza("Margherita","", 3.00,1)

    @Before
    fun setup() {
        sut = PizzaOrder()
    }

    @Test
    fun `should provide id after adding order`() {

        val result = sut.add(pizzaMargherita)

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `should include total price of pizzas and include VAT of 20% for total price`() {

        val pizzaPesto= Pizza("Pesto", "", 4.00, 1)
        sut.add(pizzaMargherita)
        sut.add(pizzaPesto)

        assertThat(sut.totalPrice()).isEqualTo(8.40)
    }


}