package com.rud.mandeumtalk.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.fragments.*

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        auth = Firebase.auth

        val currentUserUid = auth.currentUser?.email.toString()

        findViewById<Button>(R.id.writeUploadButton).setOnClickListener {

            val title = findViewById<EditText>(R.id.titleArea).text.toString()
            val contents = findViewById<EditText>(R.id.contentsArea).text.toString()

            val database = Firebase.database
            val myRef = database.getReference("Board")
            myRef.push().setValue(BoardModel(title, contents, currentUserUid))

            Toast.makeText(this, "$title\n작성 완료되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}