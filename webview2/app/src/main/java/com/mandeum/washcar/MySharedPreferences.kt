package com.mandeum.washcar

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }


    fun getString(key: String, str: String): String {
        return prefs.getString(key, str).toString()
    }

    fun setBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, value: Boolean): Boolean {
        return prefs.getBoolean(key, value)
    }


}