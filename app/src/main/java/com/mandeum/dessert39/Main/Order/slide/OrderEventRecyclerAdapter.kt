package com.mandeum.dessert39.Main.Order.slide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.Slide.CardListAdapter
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.card_type_item.view.*

class OrderEventRecyclerAdapter(private val eventItem: ArrayList<OrderEventItem>) : RecyclerView.Adapter<OrderEventRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderEventRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_event_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderEventRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(eventItem[position])
    }

    override fun getItemCount(): Int {
       return eventItem.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.dessert_name)
        private val subImage = itemView.findViewById<ImageView>(R.id.subImage)
        private val imageView = itemView.findViewById<ImageView>(R.id.dessert_image)
        fun bindItem(item: OrderEventItem) {

            title.text = item.eventName

            Glide.with(itemView)
                .load(item.imageSrc2)
                .into(subImage)

            Glide.with(itemView)
                .load(item.imageSrc)
                .into(imageView)

            itemView.setOnClickListener {
                val direction = OrderFragmentDirections.actionOrderFragmentToOrderMenuDetailFragment(item.eventName, "","", item.imageSrc)
                it.findNavController().navigate(direction)

            }
        }

    }

}