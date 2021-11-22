package com.mandeum.dessert39.SharedPreferences

import android.app.Application

class Application: Application() {

    companion object {

        lateinit var mySharedPreferences: MySharedPreferences
    }

    override fun onCreate() {
        mySharedPreferences = MySharedPreferences(applicationContext)
        super.onCreate()
    }

}