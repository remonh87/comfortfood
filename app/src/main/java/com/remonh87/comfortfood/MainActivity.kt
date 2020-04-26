package com.remonh87.comfortfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app = applicationContext as ComfortFoodApplication
        MethodChannel(
            app.flutterEngine.dartExecutor.binaryMessenger,
            "comfortfoodChannel"
        ).setMethodCallHandler(MethodChannelHandler())
    }
}
