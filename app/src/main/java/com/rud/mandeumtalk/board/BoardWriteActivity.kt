package com.rud.mandeumtalk.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.fragments.*

class BoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        findViewById<Button>(R.id.writeUploadButton).setOnClickListener {

            val title = findViewById<EditText>(R.id.titleArea).text.toString()
            val contents = findViewById<EditText>(R.id.contentsArea).text.toString()

            val database = Firebase.database
            val myRef = database.getReference("Board")
            myRef.push().setValue(BoardModel(title, contents))

            Toast.makeText(this, "$title\n작성 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.homeButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /*
        findViewById<ImageView>(R.id.homeIcon).setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.homeText).setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.portfolioIcon).setOnClickListener {
            val intent = Intent(this, PortfolioFragment::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.portfolioText).setOnClickListener {
            val intent = Intent(this, PortfolioFragment::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.boardIcon).setOnClickListener {
            val intent = Intent(this, BoardFragment::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.boardText).setOnClickListener {
            val intent = Intent(this, BoardFragment::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.contactUsIcon).setOnClickListener {
            val intent = Intent(this, ContactUsFragment::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.contactUsText).setOnClickListener {
            val intent = Intent(this, ContactUsFragment::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.accountIcon).setOnClickListener {
            val intent = Intent(this, AccountFragment::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.accountText).setOnClickListener {
            val intent = Intent(this, AccountFragment::class.java)
            startActivity(intent)
        }
         */
    }
}