package com.mandeum.dessert39.Login.ServerApi.Model.Order

import com.mandeum.dessert39.Main.Order.selectShop.select.SelectShopModel
import com.mandeum.dessert39.Main.Order.slide.OrderShopItem

data class ShopStoryModel (val connection: Boolean, val errCode: String, val list : ArrayList<SelectShopModel>)