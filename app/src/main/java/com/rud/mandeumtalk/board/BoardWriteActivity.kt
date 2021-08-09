package com.rud.mandeumtalk.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.R

class BoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        findViewById<Button>(R.id.writeUploadButton).setOnClickListener {

            val database = Firebase.database

            val title = findViewById<EditText>(R.id.titleArea).text.toString()
            val contents = findViewById<EditText>(R.id.contentsArea).text.toString()

            val myRef = database.getReference("Board")

            myRef.push().setValue(BoardModel(title, contents))
        }
    }
}