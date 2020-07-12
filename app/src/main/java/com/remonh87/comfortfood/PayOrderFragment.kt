package com.remonh87.comfortfood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.pay_order_fragment.*


class PayOrderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.pay_order_fragment, container, false)

    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val orderObserver = Observer<Order> { order ->
            order_lines_list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = OrderOverviewAdapter(order.orderLines)
            }
            order_total_price.text = order.totalPrice.toString()
        }

        viewModel.orderData.observe(viewLifecycleOwner, orderObserver)

    }
}