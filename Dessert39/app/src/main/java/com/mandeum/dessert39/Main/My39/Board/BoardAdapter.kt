package com.mandeum.dessert39.Main.My39.Board

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.My39.History.OrderHistoryAdapter
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R

class BoardAdapter(val context: Context, val boardItem : ArrayList<BoardItem>) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (boardItem[position], position)
    }

    override fun getItemCount(): Int {
        return boardItem.size
    }


    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : BoardItem, position: Int) {


            val title1  = itemView.findViewById<TextView>(R.id.emergency)
            val title2 = itemView.findViewById<TextView>(R.id.board_title)
            val content = itemView.findViewById<TextView>(R.id.content)
            val date = itemView.findViewById<TextView>(R.id.date)
            val view = itemView.findViewById<View>(R.id.empty_view)

            title1.text = item.bigTitle
            title2.text = item.smallTitle
            date.text = item.date
            content.text = item.content

            itemView.setOnClickListener {
                val direction = BoardFragmentDirections.actionBoardFragmentToBoardDetailFragment(item.bigTitle, item.smallTitle, item.content, item.date, item.image)
                it.findNavController().navigate(direction)
            }

            if (boardItem.size == 0) {
                view.isGone = true
            } else if (boardItem.size > 0 && position == boardItem.size -1) {
                view.isGone = false
            }
        }
    }


}