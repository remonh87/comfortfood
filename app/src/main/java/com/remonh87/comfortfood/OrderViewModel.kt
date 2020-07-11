package com.remonh87.comfortfood

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OrderViewModel(private val order: Order): ViewModel() {

    val orderData = MutableLiveData<Order>()

    init {
        orderData.value = order
    }

    fun addToOrder(orderLine: OrderLine){
        order.add(orderLine)
        orderData.postValue(order)
    }

}

class OrderViewModelFactory(private val order: Order): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = OrderViewModel(order) as T

}