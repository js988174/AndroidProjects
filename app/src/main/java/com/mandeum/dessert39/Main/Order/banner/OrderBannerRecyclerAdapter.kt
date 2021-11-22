package com.mandeum.dessert39.Main.Order.banner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.Slide.CardListAdapter
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.card_type_item.view.*

class OrderBannerRecyclerAdapter(private val pageList: ArrayList<OrderBannerItem>) : RecyclerView.Adapter<OrderBannerRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderBannerRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderBannerRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(pageList[position])
    }

    override fun getItemCount(): Int {
       return pageList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: OrderBannerItem) {
            val imageView = itemView.findViewById<ImageView>(R.id.order_banner_image)
            Glide.with(itemView)
                .load(item.imageBanner)
                .into(imageView)

        }

    }

}