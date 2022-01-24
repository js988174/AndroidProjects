package com.mandeum.dessert39.Login.ServerApi.Model.Order

import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuModel

data class GoodsModel(val connection:Boolean, val errCode: String, val no: Int, val name: String
, val eng_name: String, val imgHot: String, val price: Int)