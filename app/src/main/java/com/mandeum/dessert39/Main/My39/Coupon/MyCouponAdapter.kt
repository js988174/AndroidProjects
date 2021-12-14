package com.mandeum.dessert39.Main.My39.Coupon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_join.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class MyCouponAdapter (private val myCouponModel : ArrayList<MyCouponModel>, val context: Context) : RecyclerView.Adapter<MyCouponAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.my39_coupon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (myCouponModel[position])
    }

    override fun getItemCount(): Int {
        return myCouponModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : MyCouponModel) {

            val bgColor = itemView.findViewById<ImageView>(R.id.imageView38)
            val location = itemView.findViewById<TextView>(R.id.item_name)
            val title = itemView.findViewById<TextView>(R.id.item_title)
            val price = itemView.findViewById<TextView>(R.id.item_price)
            val minPrice = itemView.findViewById<TextView>(R.id.min_price)
            val start = itemView.findViewById<TextView>(R.id.start_day)
            val end = itemView.findViewById<TextView>(R.id.end_day)
            val textView = itemView.findViewById<TextView>(R.id.textView1)
            val textView2 = itemView.findViewById<TextView>(R.id.textView49)
            val textView3 = itemView.findViewById<TextView>(R.id.textView50)

            bgColor.setImageResource(item.bg)
            title.text = item.title
            location.text = item.location
            price.text = item.price
            minPrice.text = item.minPrice
            start.text = item.startDay
            end.text = item.endDay

            if (item.use) {
                bgColor.setBackgroundResource(R.drawable.coupon_bg2)
                title.setTextColor(ContextCompat.getColor(context, R.color.black2))
                location.setTextColor(ContextCompat.getColor(context, R.color.black2))
                price.setTextColor(ContextCompat.getColor(context, R.color.black2))
                minPrice.setTextColor(ContextCompat.getColor(context, R.color.black2))
                start.setTextColor(ContextCompat.getColor(context, R.color.black2))
                end.setTextColor(ContextCompat.getColor(context, R.color.black2))

                itemView.findViewById<ConstraintLayout>(R.id.use_coupon).isVisible = true
                itemView.findViewById<TextView>(R.id.expiry).isVisible = false

            } else if (item.expiry) {
                bgColor.setBackgroundResource(R.drawable.coupon_gray_bg)

                title.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))
                location.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))
                price.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))
                minPrice.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))
                start.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))
                end.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))
                textView.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))
                textView2.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))
                textView3.setTextColor(ContextCompat.getColor(context, R.color.check_NoColor))

                itemView.findViewById<ConstraintLayout>(R.id.use_coupon).isVisible = false
                itemView.findViewById<TextView>(R.id.expiry).isVisible = true

            }

        }
    }
}