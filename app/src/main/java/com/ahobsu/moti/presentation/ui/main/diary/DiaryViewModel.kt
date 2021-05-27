package com.ahobsu.moti.presentation.ui.main.diary

import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel


class DiaryViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {
    private val _diaryList = MutableLiveData<List<DiaryItemModel>>()
    val diaryList = _diaryList
}

data class DiaryItemModel(
    val days: Int,
    val month: String,
    val title: String = "",
    val content: String = "",
    val imageUrl: String = "",
    val isContent: Boolean = true,
    val isImage: Boolean = false

)