package com.rud.mandeumtalk.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.R

class ContentsRVAdapter (val context : Context,
                         val items : ArrayList<ContentModel>,
                         val keyList : ArrayList<String>,
                        val bookmarkIdList : MutableList<String>) : RecyclerView.Adapter <ContentsRVAdapter.ViewHolder> () {

    private lateinit var auth : FirebaseAuth

    interface ItemClick {
        fun onClick (view : View, position : Int)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsRVAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentsRVAdapter.ViewHolder, position: Int) {

        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
        holder.bindItems(items[position], keyList[position])
    }

    override fun getItemCount(): Int {

        return items.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems (item : ContentModel, key : String) {

            itemView.setOnClickListener {

                val intent = Intent (context, ContentsShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            contentTitle.text = item.title

            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            Glide.with(context).load(item.imageUrl).into(imageViewArea)

            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if (bookmarkIdList.contains(key)) {
                bookmarkArea.setImageResource(R.drawable.bookmarkred)
            } else {
                bookmarkArea.setImageResource(R.drawable.bookmark)
            }

            bookmarkArea.setOnClickListener {

                if (bookmarkIdList.contains(key)) {

                    val database = Firebase.database
                    val bookmarkREf = database.getReference("Bookmark_List")
                    auth = FirebaseAuth.getInstance()
                    val uid = auth.currentUser?.uid.toString()

                    bookmarkREf.child(uid).child(key).removeValue()

                } else {

                    val database = Firebase.database
                    val bookmarkREf = database.getReference("Bookmark_List")
                    auth = FirebaseAuth.getInstance()
                    val uid = auth.currentUser?.uid.toString()

                    bookmarkREf.child(uid).child(key).setValue(BookmarkModel(true))
                }
            }
        }
    }
}







