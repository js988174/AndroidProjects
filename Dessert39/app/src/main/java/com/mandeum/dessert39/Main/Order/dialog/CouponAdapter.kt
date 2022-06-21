package com.mandeum.dessert39.Main.Order.dialog

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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class CouponAdapter (private val couponModel : ArrayList<CouponModel>) : RecyclerView.Adapter<CouponAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.coupon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (couponModel[position])
    }

    override fun getItemCount(): Int {
        return couponModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : CouponModel) {

            val name = itemView.findViewById<TextView>(R.id.item_name)
            val price = itemView.findViewById<TextView>(R.id.item_price)
            val minPrice = itemView.findViewById<TextView>(R.id.min_price)
            val start = itemView.findViewById<TextView>(R.id.start_day)
            val end = itemView.findViewById<TextView>(R.id.end_day)


            name.text = item.name
            price.text = item.price
            minPrice.text = item.minPrice
            start.text = item.startDay
            end.text = item.endDay


        }
    }
}