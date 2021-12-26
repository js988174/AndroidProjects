package com.mandeum.dessert39.Login.ServerApi

import com.google.gson.JsonObject
import com.mandeum.dessert39.Login.ServerApi.Model.*
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuModel
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

        fun logoutJSONParse(json: String): LogoutModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")

            val data = LogoutModel(errorCode)

            return data
        }

        fun SnsloginJSONParse(json: String): SnsLoginModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")
            val strToken = jsonObject.optString("strToken","")
            val isFirstLogin = jsonObject.optString("isFirstLogin","")
            val cardImg = jsonObject.optString("cardImg","")

            val data = SnsLoginModel(errorCode,strToken,isFirstLogin, cardImg)

            return data
        }

        fun checkId(json: String): String {

            val jsonObject = JSONObject(json)

            return jsonObject.getString("errCode")
        }

        fun findId(json: String) : FindIdModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")
//            val userId = jsonObject.getString("userID")

            return FindIdModel(errorCode)
//            return FindIdModel(errorCode, userId)
        }

        fun findPw(json: String) : FindPwModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")

            return FindPwModel(errorCode)
        }

        fun sendSms(json: String) : SmsModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")
            val requestId = jsonObject.getString("requestId")
            val requestTime = jsonObject.getString("requestTime")
            val statusCode = jsonObject.getString("statusCode")

            return SmsModel(errCode = errorCode, requestId = requestId, requestTime =  requestTime, statusCode = statusCode)
        }

        fun termJSONParse(json: String, part: String): TermModel {
            val jsonObject = JSONObject(json)
            val errCode: String = jsonObject.optString("errCode", "")
            var term: String = jsonObject.optString("TERM", "")

            when (part) {
                "P" -> {
                    term = jsonObject.optString("PRIVACY", "")
                }
                "C" -> {
                    term = jsonObject.optString("CARD", "")
                }
                "G" -> {
                    term = jsonObject.optString("GPS", "")
                }
                "M" -> {
                    term = jsonObject.optString("MARKET", "")
                }
            }

            return TermModel(connection = true, errCode = errCode, term =  term)
        }



        fun bannerJSONParse(json: String): BannerModel {

            val jsonObject = JSONObject(json)
            val errCode: String = jsonObject.optString("errCode", "")
            val banners: String = jsonObject.optString("banners", "")
            val bannersArray = JSONArray(banners)

            val imgValue: String = JSONObject(bannersArray[0].toString()).optString("img", "")


            return BannerModel(connection = true, errCode = errCode, banner = imgValue)
        }

        fun orderBannerJSONParse(json: String): OrderBannerModel {
            val jsonObject = JSONObject(json)
            val bannerList = ArrayList<String>()
            val errCode: String = jsonObject.optString("errCode", "")
            val banners: String = jsonObject.optString("banners", "")
            val bannersArray = JSONArray(banners)

            for (index in 0 until bannersArray.length()) {
            val imgValue: String = JSONObject(bannersArray[index].toString()).optString("img", "")
                if (imgValue != "") {
                    bannerList.add(imgValue)
                }
            }

            return OrderBannerModel(connection = true, errCode = errCode, Arraylist = bannerList )
        }

        fun categoryJSONParse(json: String): CategoryModel {
            val jsonObject = JSONObject(json)
            val categoryList = ArrayList<SubMenuModel>()
            val errCode: String = jsonObject.optString("errCode", "")

            val cateList: String = jsonObject.optString("cateList", "")
            val caterListArray = JSONArray(cateList)
            val cateListObject = JSONObject(caterListArray[0].toString())

            val subCates : String = cateListObject.optString("subCates", "")
            val subCatesArray = JSONArray(subCates)

            for (index in 0 until subCatesArray.length()) {
                val subName : String = JSONObject(subCatesArray[index].toString()).optString("subName", "")

                val subOrder : Int = JSONObject(subCatesArray[index].toString()).optInt("subOrder", 0) + 10
                categoryList.add(SubMenuModel(name = subName, no = subOrder, select = true))
            }
            return CategoryModel(connection = true, errCode = errCode, list =  categoryList)
        }

        fun desertListJSONParse(json: String): DessertListModel {
            val jsonObject = JSONObject(json)
            val dessertList = ArrayList<OrderMenuModel>()
            val errCode: String = jsonObject.optString("errCode", "")

            val goodsList: String = jsonObject.optString("goodsList", "")
            val goodsListArrayList = JSONArray(goodsList)

            for (index in 0 until goodsListArrayList.length()) {
            val goodsListObject = JSONObject(goodsListArrayList[index].toString())
                val no = goodsListObject.optInt("no", 0)
                val name = goodsListObject.optString("name", "")
                val eName = goodsListObject.optString("eng_name", "")
                val imgHot = goodsListObject.optString("eng_name", "")
                val cate2 = goodsListObject.optString("cate2", "")
                val price_dessert = goodsListObject.optInt("price_dessert", 0)
                val status = goodsListObject.optString("status", "")

                dessertList.add(OrderMenuModel(id= no, Kname = name, Ename = eName, image = imgHot, category = cate2,
                price = price_dessert, status = status))
            }
            return DessertListModel(connection = true, errCode = errCode, list =  dessertList)
        }

        fun menuListJSONParse(json: String): MenuListModel {
            val jsonObject = JSONObject(json)
            val dessertList = ArrayList<OrderMenuModel>()
            val errCode: String = jsonObject.optString("errCode", "")

            val goodsList: String = jsonObject.optString("goodsList", "")
            val goodsListArrayList = JSONArray(goodsList)

            for (index in 0 until goodsListArrayList.length()) {
                val goodsListObject = JSONObject(goodsListArrayList[index].toString())
                val no = goodsListObject.optInt("no", 0)
                val name = goodsListObject.optString("name", "")
                val eName = goodsListObject.optString("eng_name", "")
                val imgPath = goodsListObject.optString("imgPath", "")
                var price = 0
                val cate1 = goodsListObject.optString("cate1", "")
                val price_hot_grande = goodsListObject.optInt("price_hot_grande", 0)
                val price_ice_grande = goodsListObject.optInt("price_ice_grande", 0)
                if (price_hot_grande != 0) {
                    price = price_hot_grande
                } else if (price_ice_grande != 0) {
                    price = price_ice_grande
                }
                val status = goodsListObject.optString("status", "")

                dessertList.add(OrderMenuModel(id= no, Kname = name, Ename = eName, image = imgPath, category = cate1,
                    price = price, status = status))
            }
            return MenuListModel(connection = true, errCode = errCode, list =  dessertList)
        }
    }

}