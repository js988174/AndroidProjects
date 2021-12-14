package com.mandeum.dessert39.Main.My39.Question

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.R

class QuestionAdapter (val context: Context, val questionItem : ArrayList<QuestionItem>, private val fragment: Fragment,) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (questionItem[position], position)
    }

    override fun getItemCount(): Int {
        return questionItem.size
    }


    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        @SuppressLint("NotifyDataSetChanged")
        fun bindItems (item : QuestionItem, position: Int) {

            val title1  = itemView.findViewById<TextView>(R.id.title)
            val content = itemView.findViewById<TextView>(R.id.content)
            val layoutExpand = itemView.findViewById<ConstraintLayout>(R.id.layout_expand)
            val up = itemView.findViewById<ImageView>(R.id.up)
            val view = itemView.findViewById<View>(R.id.empty_view)

            title1.text = item.title
            content.text = item.content

            layoutExpand.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
            if(item.isExpanded) up.setBackgroundResource(R.drawable.down) else up.setBackgroundResource(R.drawable.up)

            itemView.setOnClickListener {
                    item.isExpanded = !item.isExpanded
                    notifyDataSetChanged()

                }

            itemView.findViewById<ConstraintLayout>(R.id.btn).setOnClickListener {
                fragment.findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToCustomerQuestionFragment())
            }

            view.isGone = true
            if (position == questionItem.size - 1) {
                view.isGone = false
            }
        }
    }
}