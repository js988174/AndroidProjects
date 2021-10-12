package com.mandeum.dessert39.Intro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Home.Slide.HomeSliderRecyclerAdapter
import com.mandeum.dessert39.R

class IntroSlideAdapter(private var pageList: ArrayList<IntroPageItem>) : RecyclerView.Adapter<IntroSlideViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IntroSlideViewHolder {
        return IntroSlideViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.banner_list_item, parent,false))
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bindWithView(pageList[position])
    }

    override fun getItemCount(): Int {
       return pageList.size
    }
}