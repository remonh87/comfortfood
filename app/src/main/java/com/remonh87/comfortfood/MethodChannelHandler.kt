package com.remonh87.comfortfood

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class OrderMethodCallHandler(
    val addOrder: (line: OrderLine) -> Unit
) : MethodChannel.MethodCallHandler {

    companion object {
        const val fatalFailureCode = "FATAL"
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "completeOrder") {
            val argumentsRetrieved = call.arguments<List<*>>()
            val description = argumentsRetrieved[0]
            val price = argumentsRetrieved[1]

            if (description is String && price is Double) {
                addOrder(OrderLine(Restaurant.MIKE, description, price))
                result.success("Received")
            } else {
                result.error(
                    fatalFailureCode, "Incorrect arguments supplied",
                    "Description should be a string and price should be a double"
                )
            }
        } else {
            result.error(fatalFailureCode, "Incorrect method invoked", "")
        }
    }
}