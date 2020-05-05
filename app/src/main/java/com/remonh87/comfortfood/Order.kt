package com.remonh87.comfortfood

class Order {

    private val orderlines = mutableListOf<OrderLine>()

    fun add(line: OrderLine) {
        orderlines.add(line)
    }

    fun print(): String {

        var order = ""

        val orderLinesPerRestaurantIterator = orderlines.groupBy { it.restaurant }.iterator()
        
        while (orderLinesPerRestaurantIterator.hasNext()){
            val entry = orderLinesPerRestaurantIterator.next()
            order += "${entry.key} \t \$${getPriceForRestaurant(entry.key)}"
            order += getDescriptionOfOrderLine(entry.value)

            if(orderLinesPerRestaurantIterator.hasNext()){
                order+= "\n\n"
            }
        }

        return order
    }

    private fun getDescriptionOfOrderLine(
        orders: List<OrderLine>
    ):String {
        var description = ""
        orders.forEach {
            description += "\n- ${it.description}"
        }
        return description
    }

    private fun getPriceForRestaurant(restaurant: Restaurant): Double =
        orderlines.filter { it.restaurant == restaurant }.sumByDouble { it.price }

}


data class OrderLine(val restaurant: Restaurant, val description: String, val price: Double)

enum class Restaurant { LUIGI, MIKE }