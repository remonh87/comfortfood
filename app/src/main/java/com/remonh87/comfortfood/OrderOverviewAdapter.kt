package com.remonh87.comfortfood

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderOverviewAdapter(private val orderLines:List<OrderLine>) : RecyclerView.Adapter<OrderOverviewAdapter.OrderViewHolder>() {


    class OrderViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.order_line_item, parent, false)) {
        private val restaurantView: TextView = itemView.findViewById(R.id.item_restaurant)
        private val descriptionView: TextView = itemView.findViewById(R.id.item_order_desc)
        private val priceView: TextView = itemView.findViewById(R.id.item_order_price)

        fun bind(orderLine: OrderLine){
            restaurantView.text= orderLine.restaurant.name
            descriptionView.text = orderLine.description
            priceView.text = "\$ ${orderLine.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        Log.e("AAAAA", "BLAATTTTTTTTTTTT: ${orderLines.size}")

        val inflater = LayoutInflater.from(parent.context)
        return OrderViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = orderLines.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) = holder.bind(orderLines[position])
}


