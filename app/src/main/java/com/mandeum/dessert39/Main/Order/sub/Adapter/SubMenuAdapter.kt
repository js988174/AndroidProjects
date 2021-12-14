package com.mandeum.dessert39.Main.Order.sub.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.Card.Slide.CardListAdapter
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.R

class  SubMenuAdapter (val context: Context, val subMenuItem: ArrayList<SubMenuModel>, val menuItem : ArrayList<OrderMenuModel>,val recyclerView: RecyclerView) : RecyclerView.Adapter<SubMenuAdapter.ViewHolder>() {
    private val itemList: ArrayList<View> = ArrayList()
    private val menuList: ArrayList<OrderMenuModel> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SubMenuAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubMenuAdapter.ViewHolder, position: Int) {
        holder.bindItem(subMenuItem[position], subMenuItem.size)
    }

    override fun getItemCount(): Int {
        return subMenuItem.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var view = itemView.findViewById<View>(R.id.last_view)
        var view2 = itemView.findViewById<View>(R.id.first_view)

        fun bindItem(item: SubMenuModel, position: Int) {
            itemList.add(itemView)
            if (item.select) {

                itemView.findViewById<ConstraintLayout>(R.id.sub_menu_layout)
                    .setBackgroundResource(R.drawable.background_radius_maincolor5)

                for (i in menuItem) {
                    if (i.category == item.name) {
                        menuList.add(i)
                    }
                }
                recyclerView.adapter = OrderMenuAdapter(menuList, context)
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


            } else if (!item.select) {
                itemView.findViewById<ConstraintLayout>(R.id.sub_menu_layout)
                    .setBackgroundResource(R.drawable.background_radius_gray13)
            }

            itemView.findViewById<TextView>(R.id.sub_menu).text = item.name
            itemView.findViewById<ConstraintLayout>(R.id.sub_menu_layout).setOnClickListener {
                menuList.clear()
                for (i: Int in 0 until position) {
                    itemList[i].findViewById<ConstraintLayout>(R.id.sub_menu_layout)
                        .setBackgroundResource(R.drawable.background_radius_gray13)

                }
                itemView.findViewById<ConstraintLayout>(R.id.sub_menu_layout)
                    .setBackgroundResource(R.drawable.background_radius_maincolor5)

                for (i in menuItem) {
                    if (i.category == item.name) {
                        menuList.add(i)
                    }
                }
                recyclerView.adapter = OrderMenuAdapter(menuList, context)
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

//             if (subMenuItem.size > 0 && position == subMenuItem.size -1) {
//                view.isGone = false
//                view2.isGone = true
//            } else if (position == 0) {
//                view2.isGone = false
//                view.isGone = true
//            }


        }




    }



}