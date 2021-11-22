package com.mandeum.dessert39.Main.Order.selectShop.select

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class SelectShopAdapter (private val selectShopModel : ArrayList<SelectShopModel>,  private val context : Context) : RecyclerView.Adapter<SelectShopAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.select_shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (selectShopModel[position])
    }

    override fun getItemCount(): Int {
        return selectShopModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : SelectShopModel) {

            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            val name = itemView.findViewById<TextView>(R.id.shop_name)
            val location = itemView.findViewById<TextView>(R.id.shop_location2)
            val distance = itemView.findViewById<TextView>(R.id.shop_location)
            val startTime = itemView.findViewById<TextView>(R.id.weekday_start)
            val endTime = itemView.findViewById<TextView>(R.id.weekday_end)
            val startTime1 = itemView.findViewById<TextView>(R.id.weekend_start)
            val endTime1 = itemView.findViewById<TextView>(R.id.weekend_end)

            name.text = item.shopName
            location.text = item.shopLocation
            distance.text = item.distance
            startTime.text = item.weekdayStart
            endTime.text = item.weekdayEnd
            startTime1.text = item.weekendStart
            endTime1.text = item.weekendEnd


            Glide.with(itemView)
                .load(item.image)
                .into(imageView)

            if (item.fixNo) {
                itemView.findViewById<ImageView>(R.id.fix_no).isVisible = true
            } else if (!item.fixNo) {
                itemView.findViewById<ImageView>(R.id.fix_no).isVisible = false
            }
            if (item.fixYes) {
                itemView.findViewById<ConstraintLayout>(R.id.fix_yes).isVisible = true
            } else if (!item.fixYes) {
                itemView.findViewById<ConstraintLayout>(R.id.fix_yes).isVisible = false
            }
            if (item.event) {
                itemView.findViewById<ConstraintLayout>(R.id.event_image).isVisible = true
            } else if (!item.event) {
                itemView.findViewById<ConstraintLayout>(R.id.event_image).isVisible = false
            }

            itemView.setOnClickListener {

                val dialog = BottomSheetDialog(context, R.style.CustomAlertDialog)
                dialog.setContentView(R.layout.fragment_order_shop_detail)
                dialog.setCanceledOnTouchOutside(false)
                dialog.setCancelable(false)
                val image : ImageView = dialog.findViewById<ImageView>(R.id.imageView) as ImageView
                Glide.with(itemView).load(item.image).into(image)
                dialog.findViewById<TextView>(R.id.shop_name)?.text = item.shopName
                dialog.findViewById<TextView>(R.id.shop_location)?.text = item.shopLocation
                dialog.findViewById<TextView>(R.id.distance)?.text = item.distance
                dialog.findViewById<TextView>(R.id.start_time)?.text = item.weekdayStart
                dialog.findViewById<TextView>(R.id.end_time)?.text = item.weekdayEnd
                dialog.findViewById<TextView>(R.id.start_time1)?.text = item.weekendStart
                dialog.findViewById<TextView>(R.id.end_time1)?.text = item.weekendEnd
                dialog.findViewById<ConstraintLayout>(R.id.cancel_btn)?.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.findViewById<ConstraintLayout>(R.id.select_shop_btn)?.setOnClickListener {

                }
                dialog.show()
            }

        }
    }
}