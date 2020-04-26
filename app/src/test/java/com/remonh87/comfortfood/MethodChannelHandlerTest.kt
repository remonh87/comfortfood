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

    lateinit var sut: MethodChannelHandler


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = MethodChannelHandler()
    }

    @Test
    fun `Invoking completeOrder method with correct arguments`() {
        val call = MethodCall("completeOrder", 10.0)
        sut.onMethodCall(call, result)

        verify(exactly = 1) { result.success(any()) }
    }

    @Test
    fun `Invoking completeOrder method with incorrect arguments`() {
        val call = MethodCall("completeOrder", "Faulty string")
        sut.onMethodCall(call, result)

        verify(exactly = 1) { result.error(MethodChannelHandler.fatalFailureCode, any(), any()) }
    }

}

