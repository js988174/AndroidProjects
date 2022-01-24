package com.mandeum.dessert39.Main.Order.slide

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.Slide.CardListAdapter
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.card_type_item.view.*

class OrderShopRecyclerAdapter(private val shopItem: ArrayList<OrderShopItem>) : RecyclerView.Adapter<OrderShopRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderShopRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_shop_recommand, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderShopRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(shopItem[position])
    }

    override fun getItemCount(): Int {
       return shopItem.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: OrderShopItem) {

             val bg = itemView.findViewById<ConstraintLayout>(R.id.recommand_layout2)
             val name = itemView.findViewById<TextView>(R.id.menu_name)
             val image = itemView.findViewById<ImageView>(R.id.menu_image)

            bg.setBackgroundColor(Color.parseColor(item.bgColor))
            name.text = item.korean

            Glide.with(itemView)
                .load(item.imageSrc)
                .into(image)

            itemView.setOnClickListener {
                val direction =
                    OrderFragmentDirections.actionOrderFragmentToOrderMenuDetailFragment(item.no)
                it.findNavController().navigate(direction)
            }

        }

    }

}