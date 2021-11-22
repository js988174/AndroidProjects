package com.mandeum.dessert39.Main.Order.sub.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel

class OrderMenuDetailModel : ViewModel() {

    private var mutableLiveDataDessert: MutableLiveData<OrderMenuModel>?=null

   

    private val _text = MutableLiveData<String>().apply {

    }
    val text: LiveData<String> = _text
}