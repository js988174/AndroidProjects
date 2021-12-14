package com.mandeum.dessert39.Main.Order.sub.Adapter

data class
OrderMenuModel (
    var id: Int,
    val category: String,
    val image: String,
    val Kname: String,
    val Ename: String,
    val price: String,
    val soldOut: Boolean,
    val favorites: Boolean
    )