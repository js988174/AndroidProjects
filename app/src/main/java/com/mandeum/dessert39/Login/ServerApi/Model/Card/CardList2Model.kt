package com.mandeum.dessert39.Login.ServerApi.Model.Card

import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.Main.Card.Slide.CardTypeModel

data class CardList2Model(val connection:Boolean, val errCode: String, val list: ArrayList<CardTypeModel>)
