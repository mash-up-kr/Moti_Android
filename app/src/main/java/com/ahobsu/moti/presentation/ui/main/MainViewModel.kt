package com.ahobsu.moti.presentation.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.main.model.HomeData
import com.ahobsu.moti.presentation.ui.question.MissionActivity


class MainViewModel : BaseViewModel() {

    private val homeData = MutableLiveData<HomeData>(HomeData(4))

    fun getCountCheck(int: Int): Boolean {
        homeData.value?.let {
            if (it.daysCount >= int)
                return true
        }
        return false
    }

    fun startQuestionActivity(context: Context) {
        context.startActivity(Intent(context, MissionActivity::class.java))
    }
}