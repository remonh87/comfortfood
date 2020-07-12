package com.remonh87.comfortfood

class Order {

    private val orderlines = mutableListOf<OrderLine>()

    val orderLines get() = orderlines.toList()

    val hasOrderLines get() = orderlines.isNotEmpty()

    val totalPrice get() = orderLines.sumByDouble { it.price }

    fun add(line: OrderLine) {
        orderlines.add(line)
    }

    fun getPriceForRestaurant(restaurant: Restaurant): Double =
        orderlines.filter { it.restaurant == restaurant }.sumByDouble { it.price }

}


data class OrderLine(val restaurant: Restaurant, val description: String, val price: Double)

enum class Restaurant { LUIGI, MIKE }