package com.rud.mandeumtalk.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.rud.mandeumtalk.MainActivity
import com.rud.mandeumtalk.R
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private var isImageUpload : Boolean = false

    val storage = Firebase.storage

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        auth = Firebase.auth

        findViewById<ImageView>(R.id.backIcon).setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<ImageView>(R.id.imageView).setOnClickListener {
            val gallery : Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
            isImageUpload = true
        }

        findViewById<TextView>(R.id.writeUploadButton).setOnClickListener {

            val title = findViewById<EditText>(R.id.titleArea).text.toString()
            val contents = findViewById<EditText>(R.id.contentsArea).text.toString()
            var currentUserUid = auth.currentUser?.email.toString()
            if (currentUserUid == "null" || currentUserUid == "") {
                currentUserUid = "λΉνμ κ²μλ¬Ό"
            }
            val dateTime : String
            = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREAN).format(Calendar.getInstance().time)
            val writeDateTime : String = "μμ±ν λ μ§ : $dateTime"
//            val dateTime : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString()
            val writerUid : String = auth.currentUser?.uid.toString()

            // Validation
            if (title == "" && contents == "") {
                Toast.makeText(this, "μ λͺ©κ³Ό λ΄μ©μ μλ ₯ν΄ μ£Όμ­μμ€.", Toast.LENGTH_SHORT).show()
            } else if (title == "") {
                Toast.makeText(this, "μ λͺ©μ μλ ₯ν΄ μ£Όμ­μμ€.", Toast.LENGTH_SHORT).show()
            } else if (contents == "") {
                Toast.makeText(this, "λ΄μ©μ μλ ₯ν΄ μ£Όμ­μμ€.", Toast.LENGTH_SHORT).show()
            } else if (title != "" && contents != "") {

                val database = Firebase.database
                val myRef = database.getReference("Board")

                val key = myRef.push().key.toString()
                Log.e("keyyyy", key)

                myRef.child(key).setValue(BoardModel(title, contents, currentUserUid, writeDateTime, writerUid, key))

                if (isImageUpload == true) {
                    imageUpload(key)
                }

                Toast.makeText(this, "κ²μλ¬Ό μμ±μ΄ μλ£λμμ΅λλ€.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "μ μ μλ μ€λ₯ λ°μ", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<ImageView>(R.id.easterEgg).setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("Board")

            var currentUserEmail = auth.currentUser?.email.toString()
            if (currentUserEmail == null || currentUserEmail == "null" || currentUserEmail == "") {
                currentUserEmail = "λΉνμ κ²μλ¬Ό"
            }
            val dateTime : String
                    = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREAN).format(Calendar.getInstance().time)
            val writeDateTime : String = "μμ±ν λ μ§ : $dateTime"
            val writerUid : String = auth.currentUser?.uid.toString()

            for (i in 1..10) {

                val key = myRef.push().key.toString()

                myRef.child(key).setValue(BoardModel("$i μ±κ°λ° νλ‘μ νΈ μ λͺ© $i",
                                    "μ±κ°λ° νλ‘μ νΈ μλλ€.\n$i λ² κ²μλ¬Ό μλλ€.\nν΄λΉ κ²μλ¬Όμ κ°λ°μμ μ΄μ€ν° μκ·Έ μλλ€.\nλ°κ°μ΅λλ€. λ§λ¦μλλ€.",
                                            currentUserEmail,
                                            writeDateTime,
                                            writerUid,
                                            key))

                imageUpload(key)
            }
            Toast.makeText(this, "κ°λ°μμ μ΄μ€ν° μκ·Έκ° λ°λλμμ΅λλ€.\nν΄λΉ κ³μ μΌλ‘ 10κ°μ κ²μλ¬Όμ΄ μμ±λ©λλ€.", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun imageUpload (key : String) {

        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child(key+".png")

        val imageView = findViewById<ImageView>(R.id.uploadImage)

        // Get the data from an ImageView as bytes
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 100) {
            findViewById<ImageView>(R.id.uploadImage).isVisible = true
            findViewById<ImageView>(R.id.uploadImage).setImageURI(data?.data)
        }
    }

    override fun onBackPressed(){

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}