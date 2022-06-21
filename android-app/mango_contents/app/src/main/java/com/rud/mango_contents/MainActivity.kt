package com.rud.mango_contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val items = mutableListOf<ContentsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookmarkButton = findViewById<TextView>(R.id.bookmarkBtn)
        bookmarkButton.setOnClickListener() {

            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/iLgR6ap-84xv",
                "https://mp-seoul-image-production-s3.mangoplate.com/619788_1613652642367848.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "티바이양크레프리"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/T73jKrkWrLSo",
               "https://mp-seoul-image-production-s3.mangoplate.com/423054/400_1613639610722_34399?fit=around|738:738&crop=738:738;*,*&output-format=jpg&output-quality=80",
                "아티장베이커스"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/iLgR6ap-84xv",
                "https://mp-seoul-image-production-s3.mangoplate.com/404357/x3dr8-5cc6yudn.jpg?fit=around|738:738&crop=738:738;*,*&output-format=jpg&output-quality=80",
                "필무드"
            )
        )


        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/iLgR6ap-84xv",
                "https://mp-seoul-image-production-s3.mangoplate.com/619788_1613652642367848.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "티바이양크레프리"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/T73jKrkWrLSo",
                "https://mp-seoul-image-production-s3.mangoplate.com/423054/400_1613639610722_34399?fit=around|738:738&crop=738:738;*,*&output-format=jpg&output-quality=80",
                "아티장베이커스"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/iLgR6ap-84xv",
                "https://mp-seoul-image-production-s3.mangoplate.com/404357/x3dr8-5cc6yudn.jpg?fit=around|738:738&crop=738:738;*,*&output-format=jpg&output-quality=80",
                "필무드"
            )
        )


        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val rvAdapter = RVAdapter(baseContext, items)
        recyclerView.adapter = rvAdapter

        rvAdapter.itemClick = object : RVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                val intent = Intent(baseContext, ViewActivity::class.java)
                intent.putExtra("url", items[position].url)
                intent.putExtra("title", items[position].titleText)
                intent.putExtra("imageUrl", items[position].ImageUrl)

                startActivity(intent)


            }

        }

        recyclerView.layoutManager = GridLayoutManager(this, 2)

    }
}