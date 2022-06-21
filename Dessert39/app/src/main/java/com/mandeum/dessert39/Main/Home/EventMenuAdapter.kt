package com.mandeum.dessert39.Main.Home

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.event_menu_item.view.*

class EventMenuAdapter(val context: Context, val items: ArrayList<EventMenuModel>) : RecyclerView.Adapter<EventMenuAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventMenuAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventMenuAdapter.ViewHolder, position: Int) {

        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
       return items.size
    }


   inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

       private val Title = itemView.findViewById<TextView>(R.id.EventTitle)
       private val imageView = itemView.findViewById<ImageView>(R.id.EventImage)
       private val backGroundImage2 = itemView.findViewById<ImageView>(R.id.background_image)

       fun bindItem(item: EventMenuModel) {

           backGroundImage2.setBackgroundResource(item.backGroundImage)
           Title.text = item.title

           Glide.with(context)
               .load(item.imageUrl)
               .into(imageView)

       }
    }

}