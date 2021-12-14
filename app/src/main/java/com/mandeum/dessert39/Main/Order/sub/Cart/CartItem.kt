package com.mandeum.dessert39.Main.Order.sub.Cart

import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel

data class CartItem(var product : OrderMenuModel, var quantity: Int = 0)
