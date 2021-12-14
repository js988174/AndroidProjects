package com.mandeum.dessert39.Main.My39.Receipt

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
import com.mandeum.dessert39.Main.My39.Board.BoardFragmentDirections
import com.mandeum.dessert39.Main.My39.History.OrderHistoryFragmentDirections
import com.mandeum.dessert39.Main.My39.History.OrderHistoryModel
import com.mandeum.dessert39.R

class ReceiptAdapter(val context: Context, val receiptModel: ArrayList<ReceiptModel>) : RecyclerView.Adapter<ReceiptAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReceiptAdapter.ViewHolder {
      val view : View = LayoutInflater.from(parent.context).inflate(R.layout.receip_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(receiptModel[position])
    }

    override fun getItemCount(): Int {
       return receiptModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : ReceiptModel) {


            val type = itemView.findViewById<TextView>(R.id.type)
            val date = itemView.findViewById<TextView>(R.id.date_text)
            val type2 = itemView.findViewById<TextView>(R.id.type2)
            val price = itemView.findViewById<TextView>(R.id.receip_price)


            type.text = item.type
            date.text = item.date
            type2.text = item.type2
            price.text = item.price


            itemView.setOnClickListener {
                val direction = ReceiptFragmentDirections.actionReceiptFragmentToReceiptDetailFragment()
                it.findNavController().navigate(direction)
            }

        }
    }
}