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

class ReceiptDetailAdapter(val context: Context, val receiptDetailModel: ArrayList<ReceiptDetailModel>) : RecyclerView.Adapter<ReceiptDetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReceiptDetailAdapter.ViewHolder {
      val view : View = LayoutInflater.from(parent.context).inflate(R.layout.receipt_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(receiptDetailModel[position])
    }

    override fun getItemCount(): Int {
       return receiptDetailModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : ReceiptDetailModel) {


            val menu = itemView.findViewById<TextView>(R.id.dessert_name)
            val amount = itemView.findViewById<TextView>(R.id.dessert_quantity)
            val price = itemView.findViewById<TextView>(R.id.dessert_price)



            menu.text = item.menu
            amount.text = item.amount
            price.text = item.price

        }
    }
}