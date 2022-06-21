package com.mandeum.dessert39.SharedPreferences

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


    fun setUserId(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefs.toString(), Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_ID", input)
        editor.commit()
    }

    fun getUserId(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefs.toString(), Context.MODE_PRIVATE)
        return prefs.getString("MY_ID", "").toString()
    }

    fun setToken(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefs.toString(), Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("token", input)
        editor.commit()
    }

    fun getToken(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefs.toString(), Context.MODE_PRIVATE)
        return prefs.getString("token", "").toString()
    }

    fun setUserPass(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefs.toString(), Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_PASS", input)
        editor.commit()
    }

    fun getUserPass(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefs.toString(), Context.MODE_PRIVATE)
        return prefs.getString("MY_PASS", "").toString()
    }

    fun clearUser(context: Context) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefs.toString(), Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.commit()
    }
}