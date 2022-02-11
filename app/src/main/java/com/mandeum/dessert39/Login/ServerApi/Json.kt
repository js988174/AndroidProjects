package com.mandeum.dessert39.Login.ServerApi

import com.mandeum.dessert39.Login.ServerApi.Model.*
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardListModel
import com.mandeum.dessert39.Login.ServerApi.Model.Board.BoardViewModel
import com.mandeum.dessert39.Login.ServerApi.Model.Board.SetBoardModel
import com.mandeum.dessert39.Login.ServerApi.Model.Bookmark.BookmarkMenuAddModel
import com.mandeum.dessert39.Login.ServerApi.Model.Bookmark.BookmarkMenuModel
import com.mandeum.dessert39.Login.ServerApi.Model.Bookmark.BookmarkShopAddModel
import com.mandeum.dessert39.Login.ServerApi.Model.Bookmark.BookmarkShopModel
import com.mandeum.dessert39.Login.ServerApi.Model.Card.CardChoiceModel
import com.mandeum.dessert39.Login.ServerApi.Model.Coupon.CouponDownModel
import com.mandeum.dessert39.Login.ServerApi.Model.Coupon.CouponModel
import com.mandeum.dessert39.Login.ServerApi.Model.Coupon.UserCouponModel
import com.mandeum.dessert39.Login.ServerApi.Model.Grade.GradePointAddModel
import com.mandeum.dessert39.Login.ServerApi.Model.Grade.GradePointModel
import com.mandeum.dessert39.Login.ServerApi.Model.Home.BannerModel
import com.mandeum.dessert39.Login.ServerApi.Model.Home.WeatherModel
import com.mandeum.dessert39.Login.ServerApi.Model.Info.*
import com.mandeum.dessert39.Login.ServerApi.Model.Login.*
import com.mandeum.dessert39.Login.ServerApi.Model.Login.LikingModel
import com.mandeum.dessert39.Login.ServerApi.Model.Order.*
import com.mandeum.dessert39.Main.Order.dialog.FindShopModel
import com.mandeum.dessert39.Main.Order.selectShop.select.SelectShopModel
import com.mandeum.dessert39.Main.Order.slide.OrderNewItem
import com.mandeum.dessert39.Main.Order.slide.OrderRecommandItem
import com.mandeum.dessert39.Main.Order.slide.OrderSeasonItem
import com.mandeum.dessert39.Main.Order.slide.OrderShopItem
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

        fun weatherParser(json: String): WeatherModel {

            val jsonObject = JSONObject(json)
            val errCode: String = jsonObject.getString("errCode")
            val korStatus: String = jsonObject.optString("korStatus", "")
            val enStatus: String = jsonObject.optString("enStatus", "")

            return WeatherModel(true, errCode, korStatus, enStatus)
        }


        fun logoutJSONParse(json: String): LogoutModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")

            val data = LogoutModel(errorCode)

            return data
        }

        fun sendPush(json: String): SendPushModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")

            val data = SendPushModel(errorCode)

            return data
        }

        fun SnsloginJSONParse(json: String): SnsLoginModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")
            val errMsg = jsonObject.getString("errMsg")
            val strToken = jsonObject.optString("strToken","")
            val isFirstLogin = jsonObject.optString("isFirstLogin","")
            val cardImg = jsonObject.optString("cardImg","")

            val data = SnsLoginModel(errorCode,errMsg,strToken,isFirstLogin, cardImg)

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

        fun memberReg(json: String) : MemberRegModel {

            val jsonObject = JSONObject(json)
            val errorCode = jsonObject.getString("errCode")
            val strToken = jsonObject.getString("strToken")


            return MemberRegModel(connection = true, errCode = errorCode, strToken = strToken)
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


//        fun cardListJSONParse(json : String): CardListModel {
//            val jsonObject = JSONObject(json)
//            val cardList = ArrayList<com.mandeum.dessert39.Main.Card.Slide.CardListModel>()
//            val errCode: String = jsonObject.optString("errCode", "")
//            val cards: String = jsonObject.optString("cards", "")
//            val cardsArray = JSONArray(cards)
//
//            for (i in 0 until cardsArray.length()) {
//                val cardNo : Int = JSONObject(cardsArray[i].toString()).optInt("cardNo", 0) + 10
//                val cardName : String = JSONObject(cardsArray[i].toString()).optString("cardName", "")
//                val cardPath: String = JSONObject(cardsArray[i].toString()).optString("cardPath", "")
//            }
//
//            return CardListModel(connection = true, errCode = "", cardList = "")
//        }

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
                val imgHot = goodsListObject.optString("imgHot", "")
                val cate2 = goodsListObject.optInt("cate2", 0)
                val price_dessert = goodsListObject.optInt("price_dessert", 0)
                val status = goodsListObject.optString("status", "")

                dessertList.add(OrderMenuModel(id= no, Kname = name, Ename = eName, image = imgHot, category = cate2,
                price = "${price_dessert}원", status = status, dessertPrice = "${price_dessert}원"))
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
                val imgHot = goodsListObject.optString("imgHot", "")
                var price = 0
                val priceDessert = goodsListObject.optInt("price_dessert", 0)
                val cate1 = goodsListObject.optInt("cate1", 0)
                val price_hot_grande = goodsListObject.optInt("price_hot_grande", 0)
                val price_ice_grande = goodsListObject.optInt("price_ice_grande", 0)

                if (cate1 == 1) {
                    price = priceDessert
                } else if (cate1 != 1) {
                    if (price_hot_grande != 0) {
                        price = price_hot_grande
                    } else if (price_ice_grande != 0) {
                        price = price_ice_grande
                    }
                }


                val status = goodsListObject.optString("status", "")

                dessertList.add(OrderMenuModel(id= no, Kname = name, Ename = eName, image = imgHot, category = cate1,
                    price = "${price}원", status = status, dessertPrice = "${priceDessert}원"))
            }
            return MenuListModel(connection = true, errCode = errCode, list =  dessertList)
        }

        fun menuDetailJSONParse(json: String): GoodsModel {
            val jsonObject = JSONObject(json)
            val errCode: String = jsonObject.optString("errCode", "")
                val no = jsonObject.optInt("no", 0)
                val name = jsonObject.optString("name", "")
                val eName = jsonObject.optString("eng_name", "")
                val imgHot = jsonObject.optString("imgHot", "")
                val imgIce = jsonObject.optString("imgIce", "")
                var price = 0
                val priceDessert = jsonObject.optInt("price_dessert", 0)
                val cate1 = jsonObject.optInt("cate1", 0)
                val cate2 = jsonObject.optString("cate2", "")
                val price_hot_grande = jsonObject.optString("price_hot_grande", "")
                val price_ice_grande = jsonObject.optString("price_ice_grande", "")
                val price_hot_venti = jsonObject.optString("price_hot_venti", "")
                val price_ice_venti = jsonObject.optString("price_ice_venti", "")
                val price_hot_large = jsonObject.optString("price_hot_large", "")
                val price_ice_large = jsonObject.optString("price_ice_large", "")
                val optSpecial = jsonObject.optString("opt_special", "")
                val useOpt = jsonObject.optString("useOpt", "")
                val optSyrup = jsonObject.optString("opt_syrup", "")
                val optShot = jsonObject.optString("opt_shot", "")
                val optDecaffeine = jsonObject.optString("opt_decaffeine", "")
                val optHard = jsonObject.optString("opt_hard", "")
                val optPearl = jsonObject.optString("opt_pearl", "")
                val optNatadcoco = jsonObject.optString("opt_natadcoco", "")

            if (cate1 == 1) {
                price = priceDessert
            } else if (cate1 != 1) {
                if (price_hot_grande.toInt() != 0) {
                    price = price_hot_grande.toInt()
                } else if (price_ice_grande.toInt() != 0) {
                    price = price_ice_grande.toInt()
                }
                if (price_hot_venti.toInt() != 0) {
                    price = price_hot_venti.toInt()
                } else if (price_ice_venti.toInt() != 0) {
                    price = price_ice_venti.toInt()
                }
                if (price_hot_large.toInt() != 0) {
                    price = price_hot_large.toInt()
                } else if (price_ice_large.toInt() != 0) {
                    price = price_ice_large.toInt()
                }
            }


            return GoodsModel(connection = true, errCode = errCode, no = no, name = name, eng_name = eName, imgHot = imgHot, imgIce = imgIce
            , price = price.toString(),cate1 = cate1, cate2 = cate2, priceDessert = priceDessert,useOpt = useOpt, optSyrup = optSyrup, optShot = optShot,
                optDecaffeine = optDecaffeine, optHard = optHard, optPearl = optPearl, optNatadcoco = optNatadcoco, price_hot_grande, price_ice_grande,
            price_hot_venti, price_ice_venti, price_hot_large,price_ice_large, optSpecial = optSpecial)
        }



        fun newMenu(json: String): NewMenuModel {
            val jsonObject = JSONObject(json)
            val dessertList = ArrayList<OrderNewItem>()
            val errCode: String = jsonObject.optString("errCode", "")
            val goodsList: String = jsonObject.optString("goodsList", "")
            val goodsListArrayList = JSONArray(goodsList)

            if (goodsListArrayList.length() == 0) {
                return NewMenuModel(connection = true, errCode = errCode, list = dessertList)
            } else {
                for (index in 0 until goodsListArrayList.length()) {
                    val goodsListObject = JSONObject(goodsListArrayList[index].toString())
                    val no = goodsListObject.optInt("no", 0)
                    val name = goodsListObject.optString("name", "")
                    val imgHot   = goodsListObject.optString("imgHot", "")
                    val status = goodsListObject.optString("status", "")
                    val bg_color = goodsListObject.optString("bg_color", "")

                    dessertList.add(
                        OrderNewItem(no=no, menuName = name, menuImage = imgHot  , status = status, bgImage = bg_color)
                    )
                }
                return NewMenuModel(connection = true, errCode = errCode, list = dessertList)
            }
        }

        fun seaSonMenu(json: String): SeasonMenuModel {
            val jsonObject = JSONObject(json)
            val dessertList = ArrayList<OrderSeasonItem>()
            val errCode: String = jsonObject.optString("errCode", "")
            val goodsList: String = jsonObject.optString("goodsList", "")
            val goodsListArrayList = JSONArray(goodsList)

            if (goodsListArrayList.length() == 0) {
                return SeasonMenuModel(connection = true, errCode = errCode, list = dessertList)
            } else {
                for (index in 0 until goodsListArrayList.length()) {
                    val goodsListObject = JSONObject(goodsListArrayList[index].toString())
                    val no = goodsListObject.optInt("no", 0)
                    val name = goodsListObject.optString("name", "")
                    val eng_name = goodsListObject.optString("eng_name", "")
                    val imgHot = goodsListObject.optString("imgHot", "")
                    val status = goodsListObject.optString("status", "")
                    val price_hot_grande = goodsListObject.optInt("price_hot_grande", 0)
                    val price_ice_grande = goodsListObject.optInt("price_ice_grande", 0)
                    var price = 0
                    if (price_hot_grande != 0) {
                        price = price_hot_grande
                    } else if (price_ice_grande != 0) {
                        price = price_ice_grande
                    }
                    dessertList.add(
                        OrderSeasonItem(no=no, korean = name,english= eng_name, imageURL = imgHot ,price = "${price}원",
                            status = status)
                    )
                }
                return SeasonMenuModel(connection = true, errCode = errCode, list = dessertList)
            }
        }

        fun recommandMenu(json: String): RecommandMenuModel {
            val jsonObject = JSONObject(json)
            val dessertList = ArrayList<OrderShopItem>()
            val errCode: String = jsonObject.optString("errCode", "")
            val goodsList: String = jsonObject.optString("goodsList", "")
            val goodsListArrayList = JSONArray(goodsList)

            if (goodsListArrayList.length() == 0) {
                return RecommandMenuModel(connection = true, errCode = errCode, list = dessertList)
            } else {
                for (index in 0 until goodsListArrayList.length()) {
                    val goodsListObject = JSONObject(goodsListArrayList[index].toString())
                    val no = goodsListObject.optInt("no", 0)
                    val name = goodsListObject.optString("name", "")
                    val imgPath = goodsListObject.optString("imgHot", "")
                    val status = goodsListObject.optString("status", "")
                    val bg_color = goodsListObject.optString("bg_color", "")
                    dessertList.add(
                        OrderShopItem(no = no,
                            korean = name,
                            imageSrc = imgPath,
                            status = status,
                            bgColor = bg_color)
                    )
                }
                return RecommandMenuModel(connection = true, errCode = errCode, list = dessertList)
            }
        }

        fun adminRecommandMenu(json: String): AdminRecommandModel {
            val jsonObject = JSONObject(json)
            val dessertList = ArrayList<OrderRecommandItem>()
            val errCode: String = jsonObject.optString("errCode", "")
            val goodsList: String = jsonObject.optString("goodsList", "")
            val goodsListArrayList = JSONArray(goodsList)

            if (goodsListArrayList.length() == 0) {
                return AdminRecommandModel(connection = true, errCode = errCode, list = dessertList)
            } else {
                for (index in 0 until goodsListArrayList.length()) {
                    val goodsListObject = JSONObject(goodsListArrayList[index].toString())
                    val no = goodsListObject.optInt("no", 0)
                    val name = goodsListObject.optString("name", "")
                    val imgPath = goodsListObject.optString("imgHot", "")
                    val status = goodsListObject.optString("status", "")
                    dessertList.add(
                        OrderRecommandItem(no = no,
                            korean = name,
                            imageURL = imgPath,
                            status = status)
                    )
                }
                return AdminRecommandModel(connection = true, errCode = errCode, list = dessertList)
            }
        }

        fun nearbyStoreJSONParse(json:String): StoreModel {
            val jsonObject = JSONObject(json)

            val errCode: String = jsonObject.optString("errCode", "")
            val list = ArrayList<FindShopModel>()
            val shopList: String = jsonObject.optString("shopList", "")
            val shopListArrayList = JSONArray(shopList)

            if (shopListArrayList.length() == 0) {
                return StoreModel(connection = true, errCode = errCode, list = list)
            } else {
                for (i in 0..1) {
                    val shopListObject = JSONObject(shopListArrayList[i].toString())
                    val name = shopListObject.optString("name", "")
                    val time = shopListObject.optString("time", "")
                    val optDistance = shopListObject.optDouble("distance", 0.0)

                    var distance = ""
                    distance = if (optDistance > 1.0) {
                        String.format("%.1f", optDistance) + "km"
                    } else {
                        String.format("%.0f", optDistance * 1000) + "m"
                    }
                    val addr1 = shopListObject.optString("addr1", "")
                    val addr2 = shopListObject.optString("addr2", "")
                    val addr3 = shopListObject.optString("addr3", "")

                    list.add(
                        FindShopModel(
                            store = name,
                            address = addr1 + addr2 + addr3,
                            distance = distance,
                            time = time,
                        )
                    )
                }
                return StoreModel(connection = true, errCode = errCode, list = list)
            }
        }

        fun shopList(json:String): OrderShopMenuModel {
            val jsonObject = JSONObject(json)

            val errCode: String = jsonObject.optString("errCode", "")
            val list = ArrayList<SelectShopModel>()
            val shopList: String = jsonObject.optString("shopList", "")
            val shopListArrayList = JSONArray(shopList)



            if (shopListArrayList.length() == 0) {
                return OrderShopMenuModel(connection = true, errCode = errCode, list = list)
            } else {
                for (i in 0..1) {
                    val shopListObject = JSONObject(shopListArrayList[i].toString())

                    val imgs : String = shopListObject.optString("imgs")
                    val image = JSONArray(imgs)

                    val imgPath : String = JSONObject(image[i].toString()).optString("imgPath", "")

                    val name = shopListObject.optString("name", "")
                    val time = shopListObject.optString("time", "")
                    val optDistance = shopListObject.optDouble("distance", 0.0)

                    var distance = ""
                    distance = if (optDistance > 1.0) {
                        String.format("%.1f", optDistance) + "km"
                    } else {
                        String.format("%.0f", optDistance * 1000) + "m"
                    }
                    val addr1 = shopListObject.optString("addr1", "")
                    val addr2 = shopListObject.optString("addr2", "")
                    val addr3 = shopListObject.optString("addr3", "")

                    list.add(
                        SelectShopModel(
                            shopName = name,
                            address = addr1 + addr2 + addr3,
                            distance = distance,
                            time = time,
                            image = imgPath
                        )
                    )
                }
                return OrderShopMenuModel(connection = true, errCode = errCode, list = list)
            }
        }



//        fun cardList(json: String): CardList2Model {
//            val jsonObject = JSONObject(json)
//
//            val cardList = ArrayList<CardTypeModel>()
//            val errCode: String = jsonObject.optString("errCode", "")
//
//            val cardListArray: String = jsonObject.optString("cardList", "")
//            val CardListArrayData = JSONArray(cardListArray)
//
//
//            for (index in 0 until CardListArrayData.length()) {
//                val cateListObject = JSONObject(CardListArrayData[0].toString())
//
//                val cateName : String = cateListObject.optString("cateName", "")
//                val cardPath : String = cateListObject.optString("cardPath", "")
//
//
//                cardList.add(CardTypeModel(title = cateName, itemList = cardPath))
//            }
//            return CardList2Model(connection = true, errCode = errCode, list =  cardList)
//        }

        fun cardUser(json: String): CardChoiceModel {

            val jsonObject = JSONObject(json)
            val errCode: String = jsonObject.optString("errCode", "")
            val cardImg: String = jsonObject.optString("cardImg", "")


            return CardChoiceModel(connection = true, errCode = errCode, cardImg = cardImg)
        }

            fun liking(json: String): LikingModel {

                val jsonObject = JSONObject(json)
                val errCode: String = jsonObject.optString("errCode", "")


        return  LikingModel(connection = true, errCode =errCode)
    }

        fun userImage(json: String): UserImageModel {

            val jsonObject = JSONObject(json)
            val errCode: String = jsonObject.optString("errCode", "")
            val userImg: String = jsonObject.optString("userImg", "")


            return UserImageModel(Connection = true, errCode = errCode, userImg = userImg)
        }

        fun setOrder(json: String): SetOrderModel {

            val jsonObject = JSONObject(json)
            val errCode: String = jsonObject.optString("errCode", "")
            val orderNo: String = jsonObject.optString("order_no","")

            return SetOrderModel(errCode = errCode, orderNo = orderNo)
        }


    }


//    fun boardList(json: String): BoardListModel {
//
//
//
//        return  BoardListModel(connection = true, errCode = , page = , list = )
//    }


//    fun boardView(json: String): BoardViewModel {
//
//
//        return  BoardViewModel(connection = true, errCode = , list = )
//    }

//        fun bookmarkMenu(json: String): BookmarkMenuModel {
//
//
//        return  BookmarkMenuModel(connection = true, errCode = , page = , list = )
//    }


//    fun bookmarkShop(json: String): BookmarkShopModel {
//
//
//        return  BookmarkShopModel(connection = true, errCode = , page = , list = )
//    }


//    fun couponList(json: String): CouponModel {
//
//
//        return  CouponModel(connection = true, errCode = , page = , list = )
//    }


//    fun gradePoint(json: String): GradePointModel {
//
//
//        return  GradePointModel(connection = true, errCode = , average_kind = , average_time = , average_sanitation= ,
//            average_all = , average_cnt = )
//    }


//        fun liking(json: String): LikingModel {
//
//
//        return  LikingModel(connection = true, errCode = , cate = , drink = , brand = , b_drink =)
//    }


//    fun memberInfo(json: String): MemberInfoModel {
//
//
//        return  MemberInfoModel(connection = true, errCode = )
//    }


//    fun orderInfo(json: String): OrderInfoModel {
//
//
//        return  OrderInfoModel(connection = true, errCode = )
//    }



//    fun pushHistory(json: String): PushModel {
//
//
//        return  PushModel(Connection = true, errCode = , list =  )
//    }


//    fun shopStory(json: String): ShopStoryModel {
//
//
//        return  ShopStoryModel(connection = true , errCode = , list =  )
//    }


//    fun userConfig(json: String): UserConfigModel {
//
//
//        return  UserConfigModel(Connection = true , errCode = , event =  , review = , order = )
//    }


//    fun userCoupon(json: String): UserCouponModel {
//
//
//        return  UserCouponModel(Connection = true , errCode = , list =  )
//    }






//    fun memberMod(json: String): MemberModModel {
//
//
//        return  MemberModModel(connection = true, errCode = )
//    }


//        fun setBoard(json: String): SetBoardModel {
//
//
//        return  SetBoardModel(connection = true, errCode = , idx = )
//    }



//    fun bookmarkMenu(json: String): BookmarkMenuAddModel {
//
//
//        return  BookmarkMenuAddModel(connection = true, errCode = , page = , list =  )
//    }



//    fun bookmarkShop(json: String): BookmarkShopAddModel {
//
//
//        return  BookmarkShopAddModel(connection = true, errCode = )
//    }




//    fun couponDown(json: String): CouponDownModel {
//
//
//        return  CouponDownModel(connection = true, errCode = , no = )
//    }



//    fun addGradePoint(json: String): GradePointAddModel {
//
//
//        return  GradePointAddModel(connection = true, errCode = )
//    }




//    fun addUserConfig(json: String): UserConfigAddModel {
//
//
//        return  UserConfigAddModel(Connection = true, errCode = )
//    }


}