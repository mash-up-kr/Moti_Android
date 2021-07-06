package com.ahobsu.moti.presentation.ui.diary

import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.diary.model.DiaryItemModel

class DiaryViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {
    private val _diaryList = MutableLiveData<List<DiaryItemModel>>()
    val diaryList = _diaryList

    private val _selectedCalenderMonth = MutableLiveData<CalenderMonth>()
    val selectedCalenderMonth = _selectedCalenderMonth
    enum class  CalenderMonth {PREVIOUS,NEXT,SELECT }
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

    fun onClickCalenderMonth(select: CalenderMonth){
        _selectedCalenderMonth.value = select
    }
    fun onSelectCalender() {

    }
}
