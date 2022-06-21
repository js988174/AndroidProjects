package com.mandeum.dessert39.Login.ServerApi.Model.Order

import com.mandeum.dessert39.Main.Order.slide.OrderRecommandItem
import com.mandeum.dessert39.Main.Order.slide.OrderShopItem

data class RecommandMenuModel(val connection: Boolean, val errCode: String, val list : ArrayList<OrderShopItem> )
