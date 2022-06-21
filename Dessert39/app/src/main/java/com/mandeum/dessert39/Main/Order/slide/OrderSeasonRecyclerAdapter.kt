package com.mandeum.dessert39.Main.Order.slide

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.Slide.CardListAdapter
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.card_type_item.view.*

class OrderSeasonRecyclerAdapter(private val seasonItem: ArrayList<OrderSeasonItem>, context:Context) : RecyclerView.Adapter<OrderSeasonRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderSeasonRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_season_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderSeasonRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(seasonItem[position])
    }

    override fun getItemCount(): Int {
       return seasonItem.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: OrderSeasonItem) {

             val name = itemView.findViewById<TextView>(R.id.season_title1)
             val name2 = itemView.findViewById<TextView>(R.id.season_content1)
             val image = itemView.findViewById<ImageView>(R.id.season_image1)
             val price = itemView.findViewById<TextView>(R.id.season_price1)

            itemView.findViewById<ImageView>(R.id.sold_out).isGone = item.status == "정상"

            name.text = item.korean
            name2.text = item.english
            price.text = item.price

            Glide.with(itemView)
                .load(item.imageURL)
                .into(image)

            itemView.setOnClickListener {
                val direction =
                    OrderFragmentDirections.actionOrderFragmentToOrderDetailFragment(item.no)
                it.findNavController().navigate(direction)
            }


        }

    }

}