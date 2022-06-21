package com.mandeum.dessert39.Login.ServerApi.Model.Order

import com.mandeum.dessert39.Main.Order.slide.OrderNewItem
import com.mandeum.dessert39.Main.Order.slide.OrderSeasonItem

data class SeasonMenuModel(val connection: Boolean, val errCode: String, val list : ArrayList<OrderSeasonItem> )
