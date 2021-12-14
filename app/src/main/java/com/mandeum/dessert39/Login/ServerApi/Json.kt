package com.mandeum.dessert39.Login.ServerApi

import com.mandeum.dessert39.Login.ServerApi.LoginModel
import org.json.JSONArray
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


        fun bannerJSONParse(json: String): BannerModel {

            val jsonObject = JSONObject(json)
            val errCode: String = jsonObject.optString("errCode", "")
            val banners: String = jsonObject.optString("banners", "")
            val bannersArray = JSONArray(banners)

            val imgValue: String = JSONObject(bannersArray[0].toString()).optString("img", "")


            return BannerModel(connection = true, errCode = errCode, banner = imgValue)
        }

//        fun orderBannerJSONParse(json: String): OrderBannerModel {
//
//            val jsonObject = JSONObject(json)
//            val errCode: String = jsonObject.optString("errCode", "")
//            val banners: String = jsonObject.optString("banners", "")
//            val bannersArray = JSONArray(banners)
//
//            val imgValue: String = JSONObject(bannersArray[0].toString()).optString("img", "")
//
//
//            return BannerModel(connection = true, errCode = errCode, banner = imgValue)
//        }
    }

}