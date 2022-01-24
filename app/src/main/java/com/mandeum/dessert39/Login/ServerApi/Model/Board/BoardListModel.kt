package com.mandeum.dessert39.Login.ServerApi.Model.Board

import com.mandeum.dessert39.Main.My39.Board.BoardItem

data class BoardListModel(val connection:Boolean, val errCode: String, val page:Int, val list: ArrayList<BoardItem>)
