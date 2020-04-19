package com.remonh87.pizza.businesslogic

const val SUPPLEMENTARYPRICEXL = 3.0


class Pizza(
    val name: String,
    val description: String,
    val basePrice: Double,
    val imageUri: Int
) {
    private val toppings: List<Topping> = mutableListOf()
    var size: PizzaSize = PizzaSize.REGULAR
    private set

    internal fun addTopping(topping: Topping) {
        toppings as MutableList<Topping>
        toppings.add(topping)
    }

    internal fun changeSize(pizzaSize: PizzaSize){
        size = pizzaSize
    }

    internal val totalPrice: Double
        get() {
            return when (size) {
                PizzaSize.REGULAR -> basePrice + calculatePriceToppings()
                PizzaSize.XL -> basePrice + SUPPLEMENTARYPRICEXL + calculatePriceToppings()
            }
        }

    private fun calculatePriceToppings() = toppings.sumByDouble { it.price }

}


enum class Topping(val friendlyName: String, val description: String, val price: Double) {
    MOZZARELLA("Mozzarella", "Fresh creamy mozzarella", 1.50),
    OLIVES("Olives", "Mix of green and black olives",1.00),
    PEPPERONI("Pepperoni", "Spice up your pizza with this delicous pepperoni",2.00),
}

enum class PizzaSize { REGULAR, XL }
