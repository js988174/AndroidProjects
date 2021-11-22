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
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class FindShopAdapter (val context: Context, val findShopModel : ArrayList<FindShopModel>) : RecyclerView.Adapter<FindShopAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (findShopModel[position])
    }

    override fun getItemCount(): Int {
        return findShopModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : FindShopModel) {

            val name = itemView.findViewById<TextView>(R.id.shop_name)
            val location = itemView.findViewById<TextView>(R.id.shop_location)
            val distance = itemView.findViewById<TextView>(R.id.distance)
            val startTime = itemView.findViewById<TextView>(R.id.start_time)
            val endTime = itemView.findViewById<TextView>(R.id.end_time)
            val startTime1 = itemView.findViewById<TextView>(R.id.start_time)
            val endTime1 = itemView.findViewById<TextView>(R.id.end_time1)

            name.text = item.shopName
            location.text = item.shopLocation
            distance.text = item.distance
            startTime.text = item.weekdayStart
            endTime.text = item.weekdayEnd
            startTime1.text = item.weekendStart
            endTime1.text = item.weekendEnd


        }


    }
}