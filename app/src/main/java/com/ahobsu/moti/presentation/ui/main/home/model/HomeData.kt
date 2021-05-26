package com.ahobsu.moti.presentation.ui.main.home.model

import com.ahobsu.moti.domain.entity.MissionCard

data class HomeData(
    val answers: List<MissionCard>?
) {
    fun getCountCheck(int: Int): Boolean {
        return answers?.size?:0 >= int
    }
}