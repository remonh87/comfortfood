package com.remonh87.pizza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remonh87.pizza.ui.SelectPizzaFragment

class PizzaOrderActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pizza_order_activity)

        val fragment = SelectPizzaFragment()
        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, fragment)
            .commit()
    }

}