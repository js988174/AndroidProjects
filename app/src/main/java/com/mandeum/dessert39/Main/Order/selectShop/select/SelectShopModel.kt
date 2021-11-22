package com.mandeum.dessert39.Main.Order.selectShop.select

data class SelectShopModel (
    val shopName: String, val shopLocation: String, val distance : String, val weekdayStart : String,
    val weekdayEnd : String, val weekendStart: String, val weekendEnd: String, val event : Boolean,
    val fixNo: Boolean, val fixYes : Boolean, val image : String
        )