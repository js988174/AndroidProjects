package com.mandeum.dessert39.Login.ServerApi.Model.Bookmark

import com.mandeum.dessert39.Main.My39.Board.BoardItem
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel

data class BookmarkMenuModel(val connection:Boolean, val errCode: String, val page:Int, val list: ArrayList<OrderMenuModel>)
