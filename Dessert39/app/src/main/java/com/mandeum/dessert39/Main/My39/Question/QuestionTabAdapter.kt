package com.mandeum.dessert39.Main.My39.Question

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R

class QuestionTabAdapter(
    val context: Context, val tabItem: ArrayList<String>, val questionItem:
ArrayList<QuestionItem>, val recyclerView: RecyclerView,val fragment: Fragment): RecyclerView.Adapter<QuestionTabAdapter.ViewHolder>() {

    private val itemList: ArrayList<View> = ArrayList()
    private val tabList: ArrayList<QuestionItem> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionTabAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_tab_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionTabAdapter.ViewHolder, position: Int) {
        holder.bindItem(tabItem[position], position)
    }

    override fun getItemCount(): Int {
        return tabItem.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {



        @SuppressLint("ResourceAsColor", "CutPasteId")
        fun bindItem(item: String, position: Int) {
                itemList.add(itemView)

            val bold: Typeface = Typeface.createFromAsset(context.assets, "pretendard_bold.otf")
            val regular: Typeface = Typeface.createFromAsset(context.assets, "pretendardregular.otf")
            val tabText: TextView = itemView.findViewById<TextView>(R.id.tab_menu_text)
            val view = itemView.findViewById<View>(R.id.tab_view)

            if (position == 0) {
                view.isGone = false

                tabText.typeface = bold
                tabText.setTextColor(ContextCompat.getColor(context, R.color.black4))
            }

            view.isGone = true
            tabText.text = item.toString()
            tabList.clear()
                for (i in questionItem) {
                        tabList.add(i)
                }

            recyclerView.adapter = QuestionAdapter(context, tabList,fragment)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            itemView.setOnClickListener {
                for (itemView in itemList) {
                    val view2 = itemView.findViewById<View>(R.id.tab_view)
                    val tabText2: TextView = itemView.findViewById(R.id.tab_menu_text)
                    view2.isGone = true
                    tabText2.typeface = regular
                    tabText2.setTextColor(ContextCompat.getColor(context, R.color.colorGray8))
                }

                view.isGone = false
                tabText.typeface = bold
                tabText.setTextColor(ContextCompat.getColor(context, R.color.black4))
                tabList.clear()

                if (item.toString() == "전체") {
                    for (i in questionItem) {
                        tabList.add(i)
                    }
                } else if (item.toString() != "전체") {
                    for (i in questionItem) {
                        if (i.category == item.toString()) {
                            tabList.add(i)
                        }
                    }
                }

                recyclerView.adapter = QuestionAdapter(context, tabList, fragment)
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }

    }
}