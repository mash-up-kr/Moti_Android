package com.ahobsu.moti.presentation.ui.album.model

import com.ahobsu.moti.domain.entity.MissionCard

data class AlbumItemModel(
    val id:Int,
    val answers: List<MissionCard>?
) {
    fun getCardPart(int: Int): String? {
        val missionCards = answers?.filter {
            it.cardPart == int
        }
        return if (!missionCards.isNullOrEmpty()) missionCards[0].cardPngUrl else null
    }
}