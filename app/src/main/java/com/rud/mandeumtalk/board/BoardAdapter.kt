package com.rud.mandeumtalk.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rud.mandeumtalk.R

class BoardAdapter : RecyclerView.Adapter<BoardAdapter.ViewHolder>(){

    var items = ArrayList <BoardModel> ()
    lateinit var listener : OnBoardItemClickListener

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BoardAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(this, itemView, adapterPosition)
            }
        }
        fun setItem (item : BoardModel) {
            itemView.findViewById<TextView>(R.id.input1).text = item.title
            itemView.findViewById<TextView>(R.id.input2).text = item.contents
        }
    }
}