package com.mandeum.dessert39.Main.Order.sub.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.CardChoiceFragmentDirections
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.Main.Order.sub.search.SearchFragment
import com.mandeum.dessert39.Main.Order.sub.search.SearchFragmentDirections
import com.mandeum.dessert39.R

class OrderMenuAdapter(private val menuItem: ArrayList<OrderMenuModel>) : RecyclerView.Adapter<OrderMenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderMenuAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderMenuAdapter.ViewHolder, position: Int) {
        holder.bindItem(menuItem[position])
    }

    override fun getItemCount(): Int {
        return menuItem.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val kname = itemView.findViewById<TextView>(R.id.kname)
        private val Ename = itemView.findViewById<TextView>(R.id.ename)
        private val price = itemView.findViewById<TextView>(R.id.price)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)


        fun bindItem(item: OrderMenuModel) {

            kname.text = item.Kname
            Ename.text = item.Ename
            price.text = item.price

            Glide.with(itemView)
                .load(item.image)
                .into(imageView)

            if (item.soldOut) {
                itemView.findViewById<ImageView>(R.id.sold_out).isVisible = true
            } else if (!item.soldOut) {
                itemView.findViewById<ImageView>(R.id.sold_out).isVisible = false
            }
            if (item.favorites) {
                itemView.findViewById<ConstraintLayout>(R.id.favorite).isVisible = true
            } else if (!item.favorites) {
                itemView.findViewById<ConstraintLayout>(R.id.favorite).isVisible = false
            }

            itemView.setOnClickListener {
                val direction = OrderFragmentDirections.actionOrderFragmentToOrderMenuDetailFragment(item.Kname, item.Ename,item.price, item.image)
                it.findNavController().navigate(direction)
            }

//            itemView.setOnClickListener {
//                val direction2 = SearchFragmentDirections.actionSearchFragmentToOrderMenuDetailFragment(item.Kname, item.Ename,item.price, item.image)
//                it.findNavController().navigate(direction2)
//            }

        }


    }



}