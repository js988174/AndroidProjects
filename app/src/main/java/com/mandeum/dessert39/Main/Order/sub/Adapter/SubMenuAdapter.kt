package com.mandeum.dessert39.Main.Order.sub.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R

class  SubMenuAdapter(
    val context: Context, val subMenuItem: ArrayList<SubMenuModel>, val menuItem: ArrayList<OrderMenuModel>,
    val recyclerView: RecyclerView) : RecyclerView.Adapter<SubMenuAdapter.ViewHolder>() {

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

            val itemList: ArrayList<View> = ArrayList()
            val menuList: ArrayList<OrderMenuModel> = ArrayList()

            itemList.add(itemView)
            Log.d("test", "subMenuItem.size = ${menuList.size}") // 7 = 성공 / 나머지는 다시

            if (item.select) {
                itemView.findViewById<ConstraintLayout>(R.id.sub_menu_layout)
                    .setBackgroundResource(R.drawable.background_radius_gray13)

                for (i in menuItem) {
                    if (i.category == subMenuItem[0].no) {
                        menuList.add(i)
                        itemList.add(itemView)
                    }
                }
                recyclerView.adapter = OrderMenuAdapter(menuList, context)
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                item.select = false
            } else if (!item.select) {
                itemView.findViewById<ConstraintLayout>(R.id.sub_menu_layout)
                    .setBackgroundResource(R.drawable.background_radius_maincolor5)
            }

            itemView.findViewById<TextView>(R.id.sub_menu).text = item.name
            itemView.findViewById<ConstraintLayout>(R.id.sub_menu_layout).setOnClickListener {
                menuList.clear()

                for (i in menuItem) {
                    if (i.category == item.no) {
                        menuList.add(i)
                    }
                }
                recyclerView.adapter = OrderMenuAdapter(menuList, context)
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

        }




    }



}