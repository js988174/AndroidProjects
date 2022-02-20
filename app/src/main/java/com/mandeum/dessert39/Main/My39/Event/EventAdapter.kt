package com.mandeum.dessert39.Main.My39.Event

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.My39.Board.BoardFragmentDirections
import com.mandeum.dessert39.Main.My39.Sound.InquiriesAdapter
import com.mandeum.dessert39.Main.My39.Sound.InquiriesItem
import com.mandeum.dessert39.R

class EventAdapter  (val context: Context, val eventItem: ArrayList<EventItem>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(eventItem[position])
    }

    override fun getItemCount(): Int {
        return eventItem.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("NotifyDataSetChanged")
        fun bindItems(item: EventItem) {

            val bg = itemView.findViewById<ConstraintLayout>(R.id.parent)
            val title = itemView.findViewById<TextView>(R.id.title)
            val title2 = itemView.findViewById<TextView>(R.id.board_title)
            val startDate = itemView.findViewById<TextView>(R.id.s_day)
            val endDate = itemView.findViewById<TextView>(R.id.e_day)
            val grayTitle: Int = ContextCompat.getColor(context, R.color.colorGray8)
            val grayDate: Int = ContextCompat.getColor(context, R.color.check_NoColor)
            val image = itemView.findViewById<ImageView>(R.id.image)

            title.text = item.title
            title2.text = item.boardTitle
            startDate.text = item.startDate
            Glide.with(itemView)
                .load(item.image)
                .into(image)
            if (endDate != null) {
                endDate.text = item.endDate
            }

            if (item.title == "종료") {
                itemView.findViewById<ConstraintLayout>((R.id.parent))
                    .setBackgroundColor(Color.parseColor("#F8F8F8"))
                title.setTextColor(grayTitle)
                title2.setTextColor(grayTitle)
                startDate.setTextColor(grayDate)
                endDate.setTextColor(grayDate)
                itemView.findViewById<ImageView>(R.id.calender)
                    .setImageResource(R.drawable.gray_calender)
                itemView.findViewById<ImageView>(R.id.arrow)
                    .setImageResource(R.drawable.gray_right)
            }

            itemView.setOnClickListener {
                val direction = EventFragmentDirections.actionEventFragmentToEventDetailFragment(item.title, item.boardTitle,item.startDate, item.content, item.endDate, item.image)
                it.findNavController().navigate(direction)
            }




        }
    }
}