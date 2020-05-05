package com.remonh87.comfortfood

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class MethodChannelHandler(
    val addOrder: (line: OrderLine) -> Unit
) : MethodChannel.MethodCallHandler {

    companion object{
        const val fatalFailureCode = "FATAL"
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "completeOrder") {
            if (call.arguments is Double) {
                val price = call.arguments as Double
                addOrder(OrderLine(Restaurant.MIKE, "Burger", price ))
                result.success("Received")
            } else {
                result.error(fatalFailureCode, "Incorect arguments supplied", "Should be a double")
            }
        } else {
            result.error(fatalFailureCode, "Incorrect method invoked", "")
        }
    }
}