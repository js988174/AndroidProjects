package com.mandeum.dessert39.Main.My39.History

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Home.EventMenuAdapter
import com.mandeum.dessert39.Main.Home.EventMenuModel
import com.mandeum.dessert39.R

class ReviewAdapter(val context: Context, val reviewModel : ArrayList<ReviewItem>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (reviewModel[position])
    }

    override fun getItemCount(): Int {
        return reviewModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : ReviewItem) {

            val title = itemView.findViewById<TextView>(R.id.menu_item)

            title.text = item.title


        }
    }
}