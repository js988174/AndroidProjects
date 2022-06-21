package com.mandeum.dessert39.Main.My39.Shop.viewPager.Story

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

class ShopStoryAdapter (private val storyItem : ArrayList<ShopStoryModel>, private val context : Context) : RecyclerView.Adapter<ShopStoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.my39_story_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (storyItem[position])
    }

    override fun getItemCount(): Int {
        return storyItem.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : ShopStoryModel) {

            val imageView1 = itemView.findViewById<ImageView>(R.id.shop_image)
            val imageView2 = itemView.findViewById<ImageView>(R.id.shop_content_image)
            val title = itemView.findViewById<TextView>(R.id.shop_name)
            val date = itemView.findViewById<TextView>(R.id.shop_date)
            val content = itemView.findViewById<TextView>(R.id.shop_content)


            title.text = item.title
            date.text = item.date
            content.text = item.content


            Glide.with(itemView)
                .load(item.image)
                .into(imageView1)

            Glide.with(itemView)
                .load(item.image2)
                .into(imageView2)



        }
    }
}