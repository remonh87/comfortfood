package com.remonh87.pizza.businesslogic

import com.google.common.truth.Truth.assertThat
import com.remonh87.pizza.businesslogic.Pizza
import com.remonh87.pizza.businesslogic.PizzaSize
import com.remonh87.pizza.businesslogic.Topping
import org.junit.Test

class PizzaTest {

    @Test
    fun `should only use baseprice for standard pizza`() {
        val pizza = Pizza("Pesto", "a", 4.50, 1)

        assertThat(pizza.totalPrice).isEqualTo(4.50)
    }


    @Test
    fun `should increase total price with three euro in case pizza has xl size`() {
        val pizza = Pizza(
            "Pesto",
            "a",
            4.50,
            1
        )

        pizza.changeSize(PizzaSize.XL)
        assertThat(pizza.totalPrice).isEqualTo(7.50)
    }


    @Test
    fun `should take price of additional toppings into account`() {
        val pizza = Pizza("Pesto", "pesto", 4.50, 1)
        pizza.addTopping(Topping.MOZZARELLA)
        pizza.addTopping(Topping.PEPPERONI)

        assertThat(pizza.totalPrice).isEqualTo(4.50 + Topping.MOZZARELLA.price + Topping.PEPPERONI.price)
    }


    @Test
    fun `should take price of additional toppings into account for xl pizza`() {
        val pizza = Pizza(
            "Pesto",
            "pesto",
            4.50,
            1
        )
        pizza.changeSize(PizzaSize.XL)

        pizza.addTopping(Topping.MOZZARELLA)

        assertThat(pizza.totalPrice).isEqualTo(7.50 + Topping.MOZZARELLA.price)
    }

}