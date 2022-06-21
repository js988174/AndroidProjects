package com.mandeum.dessert39.Login.ServerApi.Model.Order

import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuModel

data class GoodsModel(val connection:Boolean, val errCode: String, val no: Int, val name: String
                      , val eng_name: String, val imgHot: String, val imgIce: String, val price: String, val cate1: Int, val cate2: String,
                      val priceDessert: Int, val useOpt: String, val optSyrup: String, var optShot : String,
                      var optDecaffeine: String, var optHard: String, var optPearl : String,
                      var optNatadcoco: String, val priceHotG: String, val priceIceG: String, val priceHotV: String, val priceIceV: String,
                      val priceHotL: String, val priceIceL : String, var optSpecial: String)