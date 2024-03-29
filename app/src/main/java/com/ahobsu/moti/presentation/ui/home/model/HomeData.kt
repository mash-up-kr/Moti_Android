package com.ahobsu.moti.presentation.ui.home.model

import com.ahobsu.moti.domain.entity.MissionCard

data class HomeData(
    val answers: List<MissionCard>?
) {
    fun getCountCheck(int: Int): Boolean {
        return answers?.size ?: 0 >= int
    }

    fun getCardPart(int: Int): String? {
        val missionCards= answers?.filter {
            it.cardPart == int
        }
        return if (!missionCards.isNullOrEmpty()) missionCards[0].cardPngUrl else null
    }
}