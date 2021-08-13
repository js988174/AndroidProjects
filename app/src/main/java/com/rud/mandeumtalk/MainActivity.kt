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
import android.content.pm.PackageManager

import android.content.pm.PackageInfo
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


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
	private fun getHashKey() {
		var packageInfo: PackageInfo? = null
		try {
			packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
		} catch (e: PackageManager.NameNotFoundException) {
			e.printStackTrace()
		}
		if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
		for (signature in packageInfo!!.signatures) {
			try {
				val md: MessageDigest = MessageDigest.getInstance("SHA")
				md.update(signature.toByteArray())
				Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
			} catch (e: NoSuchAlgorithmException) {
				Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
			}
		}
	}
}

