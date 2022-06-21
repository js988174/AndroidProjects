package com.mandeum.dessert39.Main.My39.Coupon

data class MyCouponModel (val bg: Int, val location: String, val title: String,
                          val price: String, val minPrice : String, val startDay : String, val endDay: String
                          , val use: Boolean, val expiry : Boolean
        )