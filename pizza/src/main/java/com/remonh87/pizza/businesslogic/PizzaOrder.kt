package com.remonh87.pizza.businesslogic


class PizzaOrder {
    val pizzas: Map<Int, Pizza> = mutableMapOf()
    private val vatPercentage = 1.20

    fun add(pizza: Pizza): Int {
        pizzas as MutableMap
        val id = pizzas.size + 1
        pizzas.put(id, pizza)

        return id
    }

    fun totalPrice(): Double {
        return (pizzas.values.sumByDouble { it.totalPrice }) * vatPercentage
    }
}
