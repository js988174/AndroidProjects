package com.rud.mandeumtalk

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.auth.IntroActivity
import com.rud.mandeumtalk.board.BoardActivity
import com.rud.mandeumtalk.databinding.ActivityJoinBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {

		auth = Firebase.auth

	    super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)


//		findViewById<Button>(R.id.logoutBtn).setOnClickListener {
//
//			auth.signOut()
//
//			val intent = Intent(this, IntroActivity::class.java)
//			intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//			startActivity(intent)
//		}

		}
	}
