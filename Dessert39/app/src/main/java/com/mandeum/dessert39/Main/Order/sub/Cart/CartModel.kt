package com.mandeum.dessert39.Main.Ord

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "Cart")
data class CartModel(
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "dessertId")
//    var desertId:String="",
//
//    @ColumnInfo(name = "dessertName")
//    var dessertName: String?=null,
//
//    @ColumnInfo(name = "dessertPrice")
//    var dessertPrice: Double=0.0,
//
//    @ColumnInfo(name = "dessertQuantity")
//    var dessertQuantity: Int = 0,
//
//    @ColumnInfo(name ="totalPrice")
//    var totalPrice : Double=0.0,
//
//    @ColumnInfo(name = "uid")
//    var userPhone: String = ""
//






    val image : String,
    val title : String,
    val temper : String,
    val size : String,
    val cupKinds : String,
    val dessertPrice : String,
    val shot : String,
    val water : String,
    val ice : String,
    val optionPrice : String,
    val discount : String,
    val discountPrice : String,
    val quantity : String,
    val totalPrice : String


)