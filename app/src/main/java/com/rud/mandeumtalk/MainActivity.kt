package com.rud.mandeumtalk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {

		auth = Firebase.auth

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}
