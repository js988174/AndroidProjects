package com.mandeum.dessert39.Login.ServerApi.Model.Order

import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuModel

data class DessertListModel(val connection:Boolean, val errCode: String, val list: ArrayList<OrderMenuModel>,)