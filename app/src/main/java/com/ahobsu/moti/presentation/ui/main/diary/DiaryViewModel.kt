package com.ahobsu.moti.presentation.ui.main.diary

import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel


class DiaryViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {
    private val _diaryList = MutableLiveData<List<DiaryItemModel>>()
    val diaryList = _diaryList

    init {
        val a = DiaryItemModel(
            id = 0,
            days = "12",
            month = "Sun",
            title = "오늘 비가와요. 비를 주제로\n" + "한줄 시를 써볼까요?",
            content = "만수산 드렁칡이 얽혀진들 어떠하리 …",
            imageUrl = null,
            isContent = true,
            isImage = true
        )
        val aa = listOf<DiaryItemModel>() + a + a + a + a
        _diaryList.postValue(aa)

    }
}

data class DiaryItemModel(
    val id: Int,
    val days: String,
    val month: String,
    val title: String = "",
    val content: String = "",
    val imageUrl: String? ,
    val isContent: Boolean = true,
    val isImage: Boolean = false
)