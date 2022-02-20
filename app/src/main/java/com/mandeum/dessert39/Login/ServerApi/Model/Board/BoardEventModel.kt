package com.mandeum.dessert39.Login.ServerApi.Model.Board

import com.mandeum.dessert39.Main.My39.Board.BoardItem
import com.mandeum.dessert39.Main.My39.Event.EventItem
import com.mandeum.dessert39.Main.My39.Sound.InquiriesItem

data class BoardEventModel(val connection:Boolean, val errCode: String, val page:Int, val list: ArrayList<EventItem>)
