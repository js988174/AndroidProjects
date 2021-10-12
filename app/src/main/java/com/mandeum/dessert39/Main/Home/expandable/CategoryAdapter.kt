package com.mandeum.dessert39.Main.Home.expandable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R

class CategoryAdapter(val categoryList: List<CategoryList>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var MnameTxt : TextView = itemView.findViewById(R.id.tv_list_title)
        var KnameTxt : TextView = itemView.findViewById(R.id.nameTv)
        var EnameTxt : TextView = itemView.findViewById(R.id.textView2)
        var desertImage : ImageView = itemView.findViewById(R.id.desertImage1)
        var parentText : ConstraintLayout = itemView.findViewById(R.id.parent)
        var childText : ConstraintLayout = itemView.findViewById(R.id.child)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.menu_parent,parent,false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
          val category : CategoryList = categoryList[position]
        holder.MnameTxt.text = category.Mname
        holder.KnameTxt.text = category.Kname
        holder.EnameTxt.text = category.Ename

        val isExpandable : Boolean = categoryList[position].expandable
        holder.childText.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.parentText.setOnClickListener {
            val category = categoryList[position]
            category.expandable = !category.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
      return categoryList.size
    }
}