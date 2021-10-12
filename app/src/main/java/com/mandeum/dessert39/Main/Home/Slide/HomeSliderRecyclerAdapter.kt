package com.mandeum.dessert39.Main.Home.Slide


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.banner_list_item.view.*

class HomeSliderRecyclerAdapter(val context: Context, val pageList: ArrayList<PageItem>)  : RecyclerView.Adapter<HomeSliderRecyclerAdapter.MyIntroPagerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSliderRecyclerAdapter.MyIntroPagerViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_list_item, parent, false)
        return MyIntroPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pageList.size
    }

    override fun onBindViewHolder(holder: MyIntroPagerViewHolder, position: Int) {
        holder.bindWithView(pageList[position])
    }

    inner class  MyIntroPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemImage = itemView.pager_item_image
        private val itemContent = itemView.pager_item_text
        private val itemBg = itemView.pager_item_bg

        fun bindWithView(pageItem: PageItem){
            itemImage.setImageResource(pageItem.imageSrc)
            itemContent.text = pageItem.content
            itemBg.setBackgroundResource(pageItem.bgColor)

            if(pageItem.bgColor != R.color.colorWhite){
                itemContent.setTextColor(Color.WHITE)
            }

            itemBg.setBackgroundResource(pageItem.bgColor)
        }

    }


}