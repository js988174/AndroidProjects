package com.mandeum.dessert39.Main.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.R

class EventMenuAdapter2(val context: Context, val items2: ArrayList<EventMenuModel2>) : RecyclerView.Adapter<EventMenuAdapter2.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_menu_item2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(items2[position])
    }

    override fun getItemCount(): Int {
       return items2.size
    }


   inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

       fun bindItem(item: EventMenuModel2) {
            val imageView = itemView.findViewById<ImageView>(R.id.EventImage2)


           Glide.with(context)
               .load(item.imageUrl2)
               .into(imageView)


       }
    }

}