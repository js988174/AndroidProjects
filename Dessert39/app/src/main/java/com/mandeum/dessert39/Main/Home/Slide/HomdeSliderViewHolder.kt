package com.mandeum.dessert39.Main.Home.Slide

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.banner_list_item.view.*
import kotlinx.android.synthetic.main.home_item.view.*


class HomdeSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemImage = itemView.recommand_image
    private val itemTitle = itemView.recommand_title
    private val itemContent = itemView.recommand_content
    private val itemBg = itemView.recommand_bg

    fun bindWithView(pageItem: PageItem){
        itemImage.setImageResource(pageItem.imageSrc)
        itemTitle.text = pageItem.title
        itemContent.text = pageItem.content
        itemBg.setBackgroundResource(pageItem.bgColor)

        if(pageItem.bgColor != R.color.colorWhite){
            itemContent.setTextColor(Color.WHITE)
        }

        itemBg.setBackgroundResource(pageItem.bgColor)
    }

}