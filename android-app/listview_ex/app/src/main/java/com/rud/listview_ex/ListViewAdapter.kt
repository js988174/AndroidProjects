package com.rud.listview_ex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(val List : MutableList<ListViewModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(postion: Int, convertView: View?, parent: ViewGroup?): View {

        var converView = convertView

        if (converView == null) {
            converView = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }

        val title = converView!!.findViewById<TextView>(R.id.listviewItem)
        val content = converView!!.findViewById<TextView>(R.id.listviewItem2)
        title.text = List[postion].title
        content.text = List[postion].content
        // List[0] - > ListViedwModel("a", "b")
        // List[1] - > ListViedwModel("c", "d")
        // List[2] - > ListViedwModel("e", "f")

        return converView!!

    }
}
