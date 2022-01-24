package com.mandeum.dessert39.Login.ServerApi.Model.Order

import com.mandeum.dessert39.Main.Order.slide.OrderNewItem

data class NewMenuModel(val connection: Boolean, val errCode: String,val list : ArrayList<OrderNewItem> )
