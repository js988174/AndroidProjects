package com.mandeum.dessert39.Login.ServerApi.Model.Login

data class SnsLoginModel(val errCode : String,val errMsg:String, val strToken : String, val isFirstLogin : String, val cardImg: String)