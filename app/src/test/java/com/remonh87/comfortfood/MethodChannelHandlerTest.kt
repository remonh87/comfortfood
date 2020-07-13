package com.remonh87.comfortfood

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MethodChannelHandlerTest {

    @MockK
    lateinit var result: MethodChannel.Result

    lateinit var sut: OrderMethodCallHandler

    @MockK
    lateinit var order: Order

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = OrderMethodCallHandler(order::add)
    }

    @Test
    fun `It invokes completeOrder method with correct arguments`() {
        val call = MethodCall("completeOrder", 10.0)
        sut.onMethodCall(call, result)

        verify(exactly = 1) { result.success(any()) }
    }

    @Test
    fun `It invokes injected method when succesfull`() {
        val call = MethodCall("completeOrder", 10.0)
        sut.onMethodCall(call, result)

        verify(exactly = 1) { order.add(OrderLine(Restaurant.MIKE, "Burger", 10.0)) }
    }

    @Test
    fun `It invokes completeOrder method with incorrect arguments`() {
        val call = MethodCall("completeOrder", "Faulty string")
        sut.onMethodCall(call, result)

        verify(exactly = 1) { result.error(OrderMethodCallHandler.fatalFailureCode, any(), any()) }
    }
}


