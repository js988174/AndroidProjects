package com.mandeum.dessert39.Main.My39.Shop.viewPager.Event

import android.annotation.SuppressLint
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

class ShopEventAdapter (private val eventItem : ArrayList<ShopEventModel>, private val context : Context) : RecyclerView.Adapter<ShopEventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.shop_event_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (eventItem[position])
    }

    override fun getItemCount(): Int {
        return eventItem.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        @SuppressLint("CutPasteId")
        fun bindItems(item: ShopEventModel) {

            val imageView1 = itemView.findViewById<ImageView>(R.id.dessert_image)
            val imageView2 = itemView.findViewById<ImageView>(R.id.subImage)
            val korean = itemView.findViewById<TextView>(R.id.korea_text)
            val english = itemView.findViewById<TextView>(R.id.english_text)
            val price = itemView.findViewById<TextView>(R.id.price)
            val discountPrice = itemView.findViewById<TextView>(R.id.discount_price)

            val discount: String? = item.discount
            val arrow = itemView.findViewById<ImageView>(R.id.arrow)
            val discontLayout = itemView.findViewById<ConstraintLayout>(R.id.discount_layout)



            korean.text = item.korean
            english.text = item.english
            price.text = item.price
            discountPrice.text = item.discount


            Glide.with(itemView)
                .load(item.image)
                .into(imageView1)

            Glide.with(itemView)
                .load(item.tag)
                .into(imageView2)


            if (discount.isNullOrEmpty()) {
                discontLayout.isGone = true
                arrow.isGone = true

            } else {
                discontLayout.isGone = false
                arrow.isGone = false
//                discountPrice.text = discount
            }




        }
    }
}