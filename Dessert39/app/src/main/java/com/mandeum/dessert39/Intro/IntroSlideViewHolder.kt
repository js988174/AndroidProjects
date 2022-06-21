package com.mandeum.dessert39.Intro

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.banner_list_item.view.*

class IntroSlideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val itemImage = itemView.pager_item_image
    private val itemContent = itemView.pager_item_text
    private val itemBg = itemView.pager_item_bg

    fun bindWithView(pageItem: IntroPageItem){
        itemImage.setImageResource(pageItem.imageSrc)
        itemContent.text = pageItem.content
        itemBg.setBackgroundResource(pageItem.bgColor)

        if(pageItem.bgColor != R.color.colorWhite){
            itemContent.setTextColor(Color.WHITE)
        }

        itemBg.setBackgroundResource(pageItem.bgColor)
    }

}