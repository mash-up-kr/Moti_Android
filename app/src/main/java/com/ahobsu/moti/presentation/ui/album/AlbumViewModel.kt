package com.ahobsu.moti.presentation.ui.album

import com.ahobsu.moti.domain.entity.MissionCard
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel


class AlbumViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

}

data class AlbumItemModel(
    val answers: List<MissionCard>?
) {
    fun getCardPart(int: Int): String? {
        val missionCards = answers?.filter {
            it.cardPart == int
        }
        return if (!missionCards.isNullOrEmpty()) missionCards[0].cardPngUrl else null
    }
}