package com.mandeum.dessert39.Main.My39.Shop.viewPager.Information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InformationViewModel  : ViewModel() {
    private val _bannerItemList: MutableLiveData<List<BannerItem>> = MutableLiveData()

    val bannerItemList: LiveData<List<BannerItem>>
        get() = _bannerItemList


    fun setBannerItems(list: List<BannerItem>){
        _bannerItemList.value = list
    }

}