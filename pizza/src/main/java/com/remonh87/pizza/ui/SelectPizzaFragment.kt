package com.remonh87.pizza.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.remonh87.pizza.R
import com.remonh87.pizza.businesslogic.PizzaOrder
import com.remonh87.pizza.businesslogic.PizzaSize
import kotlinx.android.synthetic.main.select_pizza_fragment.*
import kotlinx.android.synthetic.main.select_pizza_fragment.view.*

class SelectPizzaFragment() : Fragment() {

    private val viewModel: PizzaOrderViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.select_pizza_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderObserver = Observer<PizzaOrder> { newOrder ->
            val pizza = newOrder.pizzas[1]
            pizza_name.text = pizza?.name ?: ""
            pizza_description.text = pizza?.description ?: ""
            pizza_price.text = "${newOrder.totalPrice()}0"

            pizza_size_selection.setOnCheckedChangeListener { _, index ->
                if (index == R.id.medium_selection) {
                    pizza?.changeSize(PizzaSize.REGULAR)
                    pizza_price.text = "${newOrder.totalPrice()}0"

                } else if (index == R.id.xl_selection) {
                    pizza?.changeSize(PizzaSize.XL)
                    pizza_price.text = "${newOrder.totalPrice()}0"

                }
            }

            complete_order_button.setOnClickListener {
                val resultIntent = Intent()
                val bundle = Bundle()
                bundle.putString("description", "Pizza" )
                bundle.putDouble("price", newOrder?.totalPrice() ?: 0.0)
                activity?.setResult(RESULT_OK, resultIntent.putExtras(bundle))
                activity?.finish()
            }
        }

        viewModel.pizzaOrder.observe(viewLifecycleOwner, orderObserver )


        pizza_size_selection.check(R.id.medium_selection)
    }
}