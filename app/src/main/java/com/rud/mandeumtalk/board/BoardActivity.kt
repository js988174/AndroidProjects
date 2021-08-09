package com.rud.mandeumtalk.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.R

class BoardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        /*
        findViewById<Button>(R.id.testLoginBtn).setOnClickListener {
            Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.testJoinBtn).setOnClickListener {

            auth.createUserWithEmailAndPassword("abc2@abc2.com", "12341234")
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
                    }
                }

        }
         */

        findViewById<Button>(R.id.writeButton).setOnClickListener {
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)
        }
        getData()
    }
    fun getData() {

//        val database = Firebase.database
//        val myRef = database.getReference("Board")

        val myRef = Firebase.database.getReference("Board")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(BoardModel::class.java)
                    Log.d("BoardListActivity", item.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("BoardListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }
}