package com.mandeum.dessert39.Main.Home.Slide

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Home.EventMenuModel
import com.mandeum.dessert39.R

class HomeSliderRecyclerAdapter(val context: Context,val pageList: ArrayList<PageItem>) : RecyclerView.Adapter<HomdeSliderViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomdeSliderViewHolder {
        return HomdeSliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false))
    }

    override fun getItemCount(): Int {
        return pageList.size
    }

    override fun onBindViewHolder(holder: HomdeSliderViewHolder, position: Int) {
        holder.bindWithView(pageList[position])
    }

}