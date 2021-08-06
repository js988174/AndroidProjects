package com.rud.mandeumtalk.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.board.BoardActivity
import com.rud.mandeumtalk.databinding.ActivityIntroBinding


class IntroActivity : AppCompatActivity() {

	private lateinit var binding : ActivityIntroBinding

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
	//	setContentView(R.layout.activity_intro)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

		binding.loginBtn.setOnClickListener {

		}

		binding.joinBtn

		// Board
		binding.goBoard.setOnClickListener {
			val intent = Intent(this, BoardActivity::class.java)
			startActivity(intent)
		}

	}
}