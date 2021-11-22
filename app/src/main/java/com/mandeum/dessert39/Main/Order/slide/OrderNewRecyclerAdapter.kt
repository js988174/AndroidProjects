package com.mandeum.dessert39.Main.Order.slide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.Slide.CardListAdapter
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.card_type_item.view.*

class OrderNewRecyclerAdapter(private val newItem: ArrayList<OrderNewItem>) : RecyclerView.Adapter<OrderNewRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderNewRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_new_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderNewRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(newItem[position])
    }

    override fun getItemCount(): Int {
       return newItem.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: OrderNewItem) {

             val bg = itemView.findViewById<ConstraintLayout>(R.id.menu_background)
             val name = itemView.findViewById<TextView>(R.id.menu_name)
             val image = itemView.findViewById<ImageView>(R.id.menu_image)

            bg.setBackgroundResource(item.bgImage)
            name.text = item.menuName

            Glide.with(itemView)
                .load(item.menuImage)
                .into(image)


        }

    }

}