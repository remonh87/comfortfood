package com.remonh87.comfortfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order = Order()
        val viewModel: OrderViewModel by viewModels { OrderViewModelFactory(order) }

        setContentView(R.layout.activity_main)
        val app = applicationContext as ComfortFoodApplication
        MethodChannel(
            app.flutterEngine.dartExecutor.binaryMessenger,
            "comfortfoodChannel"
        ).setMethodCallHandler(MethodChannelHandler(viewModel::add))

        val fragment = LaunchScreenFragment()

        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, fragment).commit()
    }
}
