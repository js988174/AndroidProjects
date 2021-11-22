package com.mandeum.dessert39.Main.My39.Point

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

class PointAdapter (val context: Context, val pointModel : ArrayList<PointModel>) : RecyclerView.Adapter<PointAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.point_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (pointModel[position])
    }

    override fun getItemCount(): Int {
        return pointModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : PointModel) {


            val accumulatorLayout  = itemView.findViewById<ConstraintLayout>(R.id.accumulate_layout)
            val useLayout  = itemView.findViewById<ConstraintLayout>(R.id.use_layout)
            val dateLayout  = itemView.findViewById<ConstraintLayout>(R.id.date_layout)
            val validatorLayout  = itemView.findViewById<ConstraintLayout>(R.id.validate_layout)

            val accumulator = itemView.findViewById<TextView>(R.id.accumlate_point)
            val use = itemView.findViewById<TextView>(R.id.use_point)
            val kinds = itemView.findViewById<TextView>(R.id.accumlate_location)
            val date = itemView.findViewById<TextView>(R.id.date)
            val validator = itemView.findViewById<TextView>(R.id.validate_text)

                if (item.accumulate == "") {
                    accumulatorLayout.isGone = true
                    validatorLayout.isGone = true
                } else if (item.accumulate != "") {
                    accumulatorLayout.isGone = false
                    useLayout.isGone = true
                    accumulator.text = item.accumulate
                    kinds.text = item.kinds
                    date.text = item.date
                    validator.text = item.validator
                }

            if (item.use == "") {
                accumulatorLayout.isGone = false
                validatorLayout.isGone = false
            } else if (item.use != "") {
                accumulatorLayout.isGone = true
                useLayout.isGone = false
                use.text = item.use
                date.text = item.date
            }




        }
    }
}