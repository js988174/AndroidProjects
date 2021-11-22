package com.mandeum.dessert39.Main.Order.dialog

data class FindShopModel (
    val shopName: String, val shopLocation: String, val distance : String, val weekdayStart : String,
    val weekdayEnd : String, val weekendStart: String, val weekendEnd: String
        )