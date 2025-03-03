package com.remonh87.comfortfood

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.restaurant_overview_fragment.*

class LaunchScreenFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_overview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch_pizza_button.setOnClickListener {
            val intent = Intent("com.remonh87.pizza.open").setPackage(requireContext().packageName)
            startActivityForResult(intent, 1)
        }

        launch_burgers_button.setOnClickListener {
            val flutterActivity = FlutterActivity.withCachedEngine(comfortFoodEngineId)
                .build(requireContext())

            startActivity(flutterActivity)
        }

        val orderObserver = Observer<Order> { order ->
            complete_order_button.visibility = if (order.hasOrderLines) View.VISIBLE else View.GONE
        }

        viewModel.orderData.observe(viewLifecycleOwner, orderObserver)

        complete_order_button.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.main_fragment_container, PayOrderFragment())
            transaction.commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode ==1 && resultCode == AppCompatActivity.RESULT_OK){
            val description = data?.extras?.getString("description")?: ""
            val price = data?.extras?.getDouble("price")?: 0.0
            viewModel.addToOrder(OrderLine(Restaurant.LUIGI, description, price))
        }
    }
}