package com.mandeum.dessert39.Main.Card.Slide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.CardChoiceFragmentDirections
import com.mandeum.dessert39.R
import java.util.*
import kotlin.collections.ArrayList

class CardListAdapter(var TypeItem: MutableList<CardListModel>) : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(TypeItem[position])
    }

    override fun getItemCount(): Int {
        return TypeItem.size
    }


    inner class ViewHolder(itemView: View, var card: CardListModel? = null): RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: CardListModel) {
            val imageView = itemView.findViewById<ImageView>(R.id.CardImage)
            Glide.with(itemView)
                .load(item.imageUrl)
                .into(imageView)


                itemView.setOnClickListener {
                    val direction = CardChoiceFragmentDirections.actionCardChoiceFragmentToCardChargeFragment(item.imageUrl, "", "","")
                    it.findNavController().navigate(direction)
                }


        }

    }

}