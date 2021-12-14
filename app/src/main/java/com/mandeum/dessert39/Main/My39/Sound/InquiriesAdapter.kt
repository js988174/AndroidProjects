package com.mandeum.dessert39.Main.My39.Sound

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.My39.Receipt.ReceiptAdapter
import com.mandeum.dessert39.Main.My39.Receipt.ReceiptModel
import com.mandeum.dessert39.R

class InquiriesAdapter (val context: Context, val inquiriesItem: ArrayList<InquiriesItem>) : RecyclerView.Adapter<InquiriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InquiriesAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.inquiry_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(inquiriesItem[position], position)
    }

    override fun getItemCount(): Int {
        return inquiriesItem.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("NotifyDataSetChanged")
        fun bindItems(item: InquiriesItem, position: Int) {

            val bg = itemView.findViewById<Button>(R.id.box_bg)

            val up = itemView.findViewById<ImageView>(R.id.up)

            val kinds = itemView.findViewById<TextView>(R.id.kinds)
            val title = itemView.findViewById<TextView>(R.id.title)
            val date = itemView.findViewById<TextView>(R.id.date)
            val content = itemView.findViewById<TextView>(R.id.content)
            val date2 = itemView.findViewById<TextView>(R.id.date2)
            val content2 = itemView.findViewById<TextView>(R.id.content2)
            val view = itemView.findViewById<View>(R.id.gray_view)
            val layoutExpand = itemView.findViewById<ConstraintLayout>(R.id.layout_expand_content)
            val anserExpand = itemView.findViewById<ConstraintLayout>(R.id.layout_expand)

            kinds.text = item.kinds
            title.text = item.title
            date.text = item.date
            content.text = item.content
            date2.text = item.date2
            content2.text = item.content2


            layoutExpand.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
            anserExpand.visibility = if (item.anserExpanded) View.VISIBLE else View.GONE

            if(item.isExpanded) up.setImageResource(R.drawable.down) else up.setImageResource(R.drawable.up)
            var test : Boolean = false
            if (item.anser) {
                bg.setTextColor(ContextCompat.getColor(context, R.color.white))
                bg.setBackgroundResource(R.drawable.background_radius_black_button)
                bg.text = "답변완료"

            } else if (!item.anser) {
                bg.setBackgroundResource(R.drawable.background_radius_concat)
                bg.setTextColor(ContextCompat.getColor(context, R.color.black2))
                bg.text= "답변대기"
                anserExpand.isGone = true
            }


            itemView.setOnClickListener {
            if (!test) {
                item.isExpanded = !item.isExpanded
                item.anserExpanded = !item.anserExpanded
                view.isGone = false
                notifyDataSetChanged()
                test = true
            } else if (test) {
                item.isExpanded = !item.isExpanded
                view.isGone = true
                notifyDataSetChanged()
                test = false
            }
            }



        }
    }
}