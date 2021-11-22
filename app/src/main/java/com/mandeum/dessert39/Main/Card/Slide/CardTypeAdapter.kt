package com.mandeum.dessert39.Main.Card.Slide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.card_type_item.view.*

class CardTypeAdapter(val context: Context, var TypeItem : MutableList<CardTypeModel>) : RecyclerView.Adapter<CardTypeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_type_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(TypeItem[position])
    }

    override fun getItemCount(): Int {
        return TypeItem.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: CardTypeModel) {
            itemView.card_title.text = item.title

            val cardListModel = mutableListOf<CardListModel>()
            val cardListAdapter = CardListAdapter(cardListModel)

            val cardData : MutableList<CardListModel> = item.itemList

            cardListAdapter.TypeItem = cardData

            itemView.card_choice.adapter = cardListAdapter
            cardListAdapter.notifyDataSetChanged()



        }

    }

}