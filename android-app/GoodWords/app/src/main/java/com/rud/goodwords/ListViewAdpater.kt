package com.rud.goodwords

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdpater(val List : MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int {
    return List.size
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(postion: Int): Long {
        return postion.toLong()
    }

    override fun getView(postion: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }

        val listviewText = convertView?.findViewById<TextView>(R.id.listViewTextArea)
        listviewText!!.text = List[postion]

        return convertView!!
    }

}