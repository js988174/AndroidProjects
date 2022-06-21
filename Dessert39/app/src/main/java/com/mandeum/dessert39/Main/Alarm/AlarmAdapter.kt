package com.mandeum.dessert39.Main.Alarm

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class AlarmAdapter (val context: Context, val alarmData : ArrayList<AlarmData>) : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.alarm_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (alarmData[position])
    }

    override fun getItemCount(): Int {
        return alarmData.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : AlarmData) {


            val layout  = itemView.findViewById<ConstraintLayout>(R.id.alarm_item_layout)
            val icon = itemView.findViewById<ImageView>(R.id.alarm_icon)
            val title = itemView.findViewById<TextView>(R.id.alarm_title)
            val content = itemView.findViewById<TextView>(R.id.alarm_content)
            val date = itemView.findViewById<TextView>(R.id.alarm_date)
            val btn = itemView.findViewById<ConstraintLayout>(R.id.alarm_btn)
            val btnText = itemView.findViewById<TextView>(R.id.alarm_btn_text)

            if (item.title == "") {
                layout.isGone = true

            } else if (item.title != "") {

                icon.setImageResource(item.smallIcon)
                title.text = item.title
                if (item.content == "") {
                    content.isGone = true
                } else if (item.content != "") {
                    content.isGone = false
                    content.text = item.content
                }
                date.text = item.dateFormat
                if (item.button == "") {
                    btn.isGone = true
                } else if (item.button != "") {
                    btn.isGone = false
                    btnText.text = item.button
                }
            }



        }
    }
}