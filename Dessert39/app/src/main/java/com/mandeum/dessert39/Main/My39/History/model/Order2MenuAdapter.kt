package com.mandeum.dessert39.Main.My39.History.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R

class Order2MenuAdapter(private val order2MenuModel: ArrayList<Order2MenuModel>, private val context: Context) : RecyclerView.Adapter<Order2MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.order2_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (order2MenuModel[position])
    }

    override fun getItemCount(): Int {
        return order2MenuModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : Order2MenuModel) {

            val name = itemView.findViewById<TextView>(R.id.dessert_name)
            val temper = itemView.findViewById<TextView>(R.id.temper)
            val size = itemView.findViewById<TextView>(R.id.size)
            val cup = itemView.findViewById<TextView>(R.id.cup)
            val price1 = itemView.findViewById<TextView>(R.id.basic_price)
            val shot = itemView.findViewById<TextView>(R.id.shot)
            val water = itemView.findViewById<TextView>(R.id.water)
            val ice = itemView.findViewById<TextView>(R.id.ice)
            val optionPrice = itemView.findViewById<TextView>(R.id.custom_price)
            val quantity = itemView.findViewById<TextView>(R.id.number1)
            val total = itemView.findViewById<TextView>(R.id.count_price)

            name.text = item.title
            temper.text = item.temper
            size.text = item.size
            cup.text = item.cupKinds
            price1.text = item.dessertPrice
            shot.text = item.shot
            water.text = item.water
            ice.text = item.ice
            optionPrice.text = item.optionPrice
            quantity.text = item.quantity
            total.text = item.totalPrice



        }
    }
}