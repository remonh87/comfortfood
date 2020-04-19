package com.remonh87.pizza.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.remonh87.pizza.businesslogic.PizzaCatalogue
import com.remonh87.pizza.businesslogic.PizzaOrder

class PizzaOrderViewModel() : ViewModel() {

    private var catalogue = PizzaCatalogue()

    val pizzaOrder: MutableLiveData<PizzaOrder> by lazy {
        MutableLiveData<PizzaOrder>()
    }

    init {
        pizzaOrder.value = PizzaOrder()
        pizzaOrder.value?.add(catalogue.pizzas.first())
    }

    @VisibleForTesting
    constructor(pizzaCatalogue: PizzaCatalogue) : this() {
        catalogue = pizzaCatalogue
    }

}