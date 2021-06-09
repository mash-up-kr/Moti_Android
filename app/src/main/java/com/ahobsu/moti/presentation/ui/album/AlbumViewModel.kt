package com.ahobsu.moti.presentation.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.entity.MissionCard
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.album.model.AlbumItemModel


class AlbumViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

    private val _albumList = MutableLiveData<List<AlbumItemModel>>()
    val albumList: LiveData<List<AlbumItemModel>> = _albumList

    init {
        TODO("api 정리해서 어떻게 사용할지 확정하기")
        val a= AlbumItemModel(0, listOf(
            MissionCard(answerId=0, missionId=0, cardPart=1, cardPngUrl="https://cdn.moti.company/parts/1_1.png"),
            MissionCard(answerId=0, missionId=0, cardPart=2, cardPngUrl="https://cdn.moti.company/parts/2_2.png"),
            MissionCard(answerId=0, missionId=0, cardPart=3, cardPngUrl="https://cdn.moti.company/parts/3_3.png"),
            MissionCard(answerId=0, missionId=0, cardPart=4, cardPngUrl="https://cdn.moti.company/parts/4_1.png"),
            MissionCard(answerId=0, missionId=0, cardPart=5, cardPngUrl="https://cdn.moti.company/parts/5_2.png"),
            MissionCard(answerId=0, missionId=0, cardPart=6, cardPngUrl="https://cdn.moti.company/parts/6_3.png")
        ))

        _albumList.value= listOf(a,a,a,a,a,a,a)
    }
}

