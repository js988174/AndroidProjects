package com.rud.list_view2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val list_item = mutableListOf<String>()

//        list_item.add("A")
//        list_item.add("B")
//        list_item.add("C")

        val list_item2 = mutableListOf<ListViewModel>()
        list_item2.add(ListViewModel("a", "b"))
        list_item2.add(ListViewModel("c", "d"))
        list_item2.add(ListViewModel("e", "f"))

        val listview = findViewById<ListView>(R.id.mainListView)

        val listadapter = ListViewAdapter(list_item2)
        listview.adapter = listadapter

        listview.setOnItemClickListener { adapterView, view, i, l ->

          Toast.makeText(this, list_item2[i].text1, Toast.LENGTH_LONG).show()
        }

    }
}