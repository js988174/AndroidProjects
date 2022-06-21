package com.mandeum.dessert39.Intro.Onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mandeum.dessert39.Join.JoinActivity
import com.mandeum.dessert39.Login.LoginActivity
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOnboarding3Binding

class Onboarding3Fragment : Fragment() {

    private lateinit var binding: FragmentOnboarding3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentOnboarding3Binding.inflate(layoutInflater)

        // 로그인 버튼
        binding.loginBtn.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)

        }

        // 회원가입 버튼
        binding.joinBtn.setOnClickListener {
            val intent = Intent(activity, JoinActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        return binding.root
    }


}