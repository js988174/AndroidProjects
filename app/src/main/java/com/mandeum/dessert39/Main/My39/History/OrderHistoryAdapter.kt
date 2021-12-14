package com.mandeum.dessert39.Main.My39.History

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class OrderHistoryAdapter (val context: Context, val historyModel : ArrayList<OrderHistoryModel>) : RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.my39_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (historyModel[position])
    }

    override fun getItemCount(): Int {
        return historyModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : OrderHistoryModel) {


            val image  = itemView.findViewById<ImageView>(R.id.history_imageView)
            val date = itemView.findViewById<TextView>(R.id.day_text)
            val title = itemView.findViewById<TextView>(R.id.item_name)
            val location = itemView.findViewById<TextView>(R.id.item_location)

            date.text = item.date
            title.text = item.title
            location.text = item.location

            Glide.with(itemView)
                .load(item.imageView)
                .into(image)

            if (item.status) {
                itemView.findViewById<TextView>(R.id.status).isVisible = true
            } else if (!item.status) {
                itemView.findViewById<TextView>(R.id.status).isVisible = false
            }

            itemView.setOnClickListener {
                val direction = OrderHistoryFragmentDirections.actionOrderHistoryFragmentToOrderHistoryDetailFragment(item.location)
                it.findNavController().navigate(direction)
            }

        }
    }
}