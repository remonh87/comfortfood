package com.remonh87.comfortfood

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {
    private val order = Order()
    private val viewModel: OrderViewModel by viewModels { OrderViewModelFactory(order) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        val app = applicationContext as ComfortFoodApplication
        MethodChannel(
            app.flutterEngine.dartExecutor.binaryMessenger,
            "comfortfoodChannel"
        ).setMethodCallHandler(OrderMethodCallHandler(viewModel::addToOrder))

        val fragment = LaunchScreenFragment()

        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, fragment)
            .commit()
    }

}
