package com.mandeum.dessert39.Main.My39.Card

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.My39.History.OrderHistoryFragmentDirections
import com.mandeum.dessert39.Main.My39.History.OrderHistoryModel
import com.mandeum.dessert39.R

class CardHistoryAdapter(val context: Context, val cardHistoryModel: ArrayList<CardHistoryModel>) : RecyclerView.Adapter<CardHistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardHistoryAdapter.ViewHolder {
      val view : View = LayoutInflater.from(parent.context).inflate(R.layout.card_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(cardHistoryModel[position])
    }

    override fun getItemCount(): Int {
       return cardHistoryModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : CardHistoryModel) {


            val image  = itemView.findViewById<ImageView>(R.id.card_image)
            val type = itemView.findViewById<TextView>(R.id.kinds)
            val date = itemView.findViewById<TextView>(R.id.date_text)
            val plus = itemView.findViewById<TextView>(R.id.price)
            val minus = itemView.findViewById<TextView>(R.id.price2)


            type.text = item.type
            date.text = item.date
            plus.text = item.plus
            minus.text = item.minus

            Glide.with(itemView)
                .load(item.image)
                .into(image)

            when (item.type) {
                "일반 충전" -> {
                    itemView.findViewById<Button>(R.id.button).isVisible = true
                    itemView.findViewById<ConstraintLayout>(R.id.text_plus).isVisible = true
                    itemView.findViewById<ConstraintLayout>(R.id.text_minus).isVisible = false
                }
                "자동 충전" -> {
                    itemView.findViewById<Button>(R.id.button).isVisible = true
                    itemView.findViewById<ConstraintLayout>(R.id.text_plus).isVisible = true
                    itemView.findViewById<ConstraintLayout>(R.id.text_minus).isVisible = false
                }
                "자동 충전 취소" -> {
                    itemView.findViewById<Button>(R.id.button).isVisible = false
                    itemView.findViewById<ConstraintLayout>(R.id.text_plus).isVisible = false
                    itemView.findViewById<ConstraintLayout>(R.id.text_minus).isVisible = true
                }
            }


        }
    }
}