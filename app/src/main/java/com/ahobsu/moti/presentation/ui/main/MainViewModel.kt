package com.ahobsu.moti.presentation.ui.main

import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.main.model.HomeData


class MainViewModel : BaseViewModel() {

    val homeData = MutableLiveData<HomeData>()

    fun getCountCheck(int: Int):Boolean {
        homeData.value?.let {
            if (it.daysCount >= int)
                return true
        }
        return false
    }
}