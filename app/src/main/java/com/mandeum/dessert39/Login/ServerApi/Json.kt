package com.mandeum.dessert39.Login.ServerApi

import com.mandeum.dessert39.Login.ServerApi.LoginModel
import org.json.JSONObject

class Json {
    companion object {

        fun loginJSONParse(json: String): LoginModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")
            val strToken = jsonObject.optString("strToken","")
            val isFirstLogin = jsonObject.optString("isFirstLogin","")

            val data = LoginModel(errorCode,strToken,isFirstLogin)

            return data
        }
    }

}