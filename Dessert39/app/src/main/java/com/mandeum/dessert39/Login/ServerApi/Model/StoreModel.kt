package com.mandeum.dessert39.Login.ServerApi.Model

import com.mandeum.dessert39.Main.Order.dialog.FindShopModel
import com.mandeum.dessert39.Main.Order.selectShop.select.SelectShopModel

data class StoreModel(val connection: Boolean, val errCode: String,
        val list : ArrayList<FindShopModel>
)