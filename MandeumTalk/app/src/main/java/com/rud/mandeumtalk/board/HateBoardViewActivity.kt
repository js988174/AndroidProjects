package com.rud.mandeumtalk.board

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.R
import kotlinx.android.synthetic.main.activity_board_view.*

class HateBoardViewActivity : AppCompatActivity() {

//    private lateinit var auth : FirebaseAuth

    lateinit var myRef : DatabaseReference

    lateinit var builder : AlertDialog.Builder
    lateinit var alertDialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hate_board_view)

        val title = intent.getStringExtra("Board Title").toString()
        val content = intent.getStringExtra("Board Content").toString()
        val writer = intent.getStringExtra("Board writer").toString()
        val dateTime = intent.getStringExtra("Board dateTime").toString()
//        val writerUid = intent.getStringExtra("Board Writer Uid").toString()
        val key = intent.getStringExtra("Board Key").toString()

        findViewById<TextView>(R.id.titleArea).text = title
        findViewById<TextView>(R.id.contentArea).text = content
        findViewById<TextView>(R.id.writerArea).text = writer
        findViewById<TextView>(R.id.dateTimeArea).text = dateTime

        getImageData(key)

        findViewById<Button>(R.id.sueButton).setOnClickListener {

            showDialog(key)
        }

        findViewById<ImageView>(R.id.backIcon).setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun showDialog(key : String) {

        try{
            var str_tittle = "????????? ?????? ??????"
            var str_message = "?????? ???????????? ????????? ?????????????????????????"
            var str_buttonOK = "?????? ??????"
            var str_buttonNO = "?????? ??????"

            builder = AlertDialog.Builder(this)
            builder.setTitle(str_tittle) //????????? ????????? ??????
            builder.setIcon(R.drawable.accountmainpink) // ????????? ?????????
            builder.setMessage(str_message) //????????? ?????? ??????
//            builder.setCancelable(false) //?????? ???????????? ???????????? ???????????? ?????????????????? ??????
            builder.setPositiveButton(str_buttonOK, DialogInterface.OnClickListener { dialog, which ->

                Toast.makeText(this, "????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
                Firebase.database.getReference("Hate_List").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child(key).removeValue()

                val intent = Intent(this, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
//                finish()

            })
            builder.setNegativeButton(str_buttonNO, DialogInterface.OnClickListener { dialog, which ->

                Toast.makeText(this, "????????? ????????? ???????????????.", Toast.LENGTH_SHORT).show()
                finish()
            })

            alertDialog = builder.create()
            try {
                alertDialog.show()
            }
            catch (e : Exception){
                e.printStackTrace()
            }
        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }

    private fun getImageData(key: String) {

        val storageReference = Firebase.storage.reference.child(key + ".png")
        val imageViewFromFB = findViewById<ImageView>(R.id.boardImage)

        storageReference.downloadUrl.addOnCompleteListener({ task ->
            if (task.isSuccessful) {
                Glide.with(this).load(task.result).into(imageViewFromFB)
            } else {

            }
        })
    }
}