package com.mandeum.dessert39.Main.Order.slide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.Slide.CardListAdapter
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.card_type_item.view.*

class OrderRecommandRecyclerAdapter(private val recommandItem: ArrayList<OrderRecommandItem>) : RecyclerView.Adapter<OrderRecommandRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderRecommandRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_user_recommand, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderRecommandRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(recommandItem[position])
    }

    override fun getItemCount(): Int {
       return recommandItem.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.dessert_name)
        private val subImage = itemView.findViewById<ImageView>(R.id.subImage)
        private val imageView = itemView.findViewById<ImageView>(R.id.dessert_image)

        fun bindItem(item: OrderRecommandItem) {

            val name = itemView.findViewById<TextView>(R.id.dessert_name)
            val image = itemView.findViewById<ImageView>(R.id.dessert_image)

            itemView.findViewById<ImageView>(R.id.subImage).isGone = item.status == "정상"
            name.text = item.korean

            Glide.with(itemView)
                .load(item.imageURL)
                .into(image)

            itemView.setOnClickListener {
                val direction =
                    OrderFragmentDirections.actionOrderFragmentToOrderMenuDetailFragment(item.no)
                it.findNavController().navigate(direction)
            }

        }

    }

}