package com.mandeum.dessert39.Main.My39.Shop

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
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class My39ShopAdapter (private val shopModel : ArrayList<My39ShopModel>, private val context : Context) : RecyclerView.Adapter<My39ShopAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.select_shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (shopModel[position])
    }

    override fun getItemCount(): Int {
        return shopModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : My39ShopModel) {

            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            val name = itemView.findViewById<TextView>(R.id.shop_name)
            val location = itemView.findViewById<TextView>(R.id.shop_location2)
            val distance = itemView.findViewById<TextView>(R.id.shop_location)
            val startTime = itemView.findViewById<TextView>(R.id.weekday)


            name.text = item.shopName
            location.text = item.shopLocation
            distance.text = item.distance
            startTime.text = item.weekdayStart



            Glide.with(itemView)
                .load(item.image)
                .into(imageView)

            if (item.fixNo) {
                itemView.findViewById<ImageView>(R.id.fix_no).isVisible = true
            } else if (!item.fixNo) {
                itemView.findViewById<ImageView>(R.id.fix_no).isVisible = false
            }
            if (item.fixYes) {
                itemView.findViewById<ConstraintLayout>(R.id.fix_yes).isVisible = true
            } else if (!item.fixYes) {
                itemView.findViewById<ConstraintLayout>(R.id.fix_yes).isVisible = false
            }

            itemView.setOnClickListener {
                val direction = My39ShopFragmentDirections.actionMy39ShopFragmentToMy39ShopDetailFragment()
                it.findNavController().navigate(direction)
            }


        }
    }
}