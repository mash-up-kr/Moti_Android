package com.ahobsu.moti.presentation.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.diary.model.DiaryItemModel

class DiaryViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {
    private val _diaryList = MutableLiveData<List<DiaryItemModel>>()
    val diaryList: LiveData<List<DiaryItemModel>> = _diaryList

    private val _selectedCalender = MutableLiveData<Unit>()
    val selectedCalender: LiveData<Unit> = _selectedCalender

    private val _selectMonthSpinner = MutableLiveData<Boolean>(false)
    val selectMonthSpinner: LiveData<Boolean> = _selectMonthSpinner

    private val _selectedMonthBtn = MutableLiveData<Unit>()
    val selectedMonthBtn: LiveData<Unit> = _selectedMonthBtn

    private val _clickCalenderMonth = MutableLiveData<CalenderMonth>()
    val selectedCalenderMonth: LiveData<CalenderMonth> = _clickCalenderMonth
    enum class CalenderMonth { PREVIOUS, NEXT, SELECT }

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
    fun selectMonth(){
        _selectMonthSpinner.value = true
    }

    fun selectedMonth(boolean: Boolean){
        _selectMonthSpinner.value = false
        if (boolean){
            _selectedMonthBtn.value = Unit
        }
    }

    fun onClickCalenderMonth(select: CalenderMonth){
        _clickCalenderMonth.value = select
    }
    fun onSelectCalender() {
        _selectedCalender.value = Unit
    }
}
