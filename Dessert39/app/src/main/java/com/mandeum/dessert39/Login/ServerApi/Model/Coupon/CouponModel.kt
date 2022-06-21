package com.mandeum.dessert39.Login.ServerApi.Model.Coupon



data class CouponModel(val connection:Boolean, val errCode: String, val page:Int, val list: ArrayList<CouponModel>)
