package com.rud.val_log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val test = "테스트 테스트 테스트 테스트 테스트123123"

        Log.e("MainActivity", test) // 오류
        Log.w("MainActivity", test) // 경고
        Log.i("MainActivity", test) // 정보
        Log.d("MainActivity", test) // 디버그
        Log.v("MainActivity", test) // 상세

        Toast.makeText(this, test, Toast.LENGTH_LONG).show()

    }
}