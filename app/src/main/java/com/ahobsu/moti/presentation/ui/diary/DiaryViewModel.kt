package com.ahobsu.moti.presentation.ui.diary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.AnswerUseCase
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.diary.model.DiaryItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class DiaryViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {
    private val _diaryList = MutableLiveData<List<DiaryItemModel>>()
    val diaryList: LiveData<List<DiaryItemModel>> = _diaryList

    private val _writeDayList = MutableLiveData<List<String>>()
    val writeDayList: LiveData<List<String>> = _writeDayList

    private val _selectedCalender = MutableLiveData<Unit>()
    val selectedCalender: LiveData<Unit> = _selectedCalender

    private val _selectMonthSpinner = MutableLiveData<Boolean>(false)
    val selectMonthSpinner: LiveData<Boolean> = _selectMonthSpinner

    private val _selectedMonthBtn = MutableLiveData<Unit>()
    val selectedMonthBtn: LiveData<Unit> = _selectedMonthBtn

    private val _month = MutableLiveData<String>()
    val month: LiveData<String> = _month

    private val _clickCalenderMonth = MutableLiveData<CalenderMonth>()
    val selectedCalenderMonth: LiveData<CalenderMonth> = _clickCalenderMonth
    enum class CalenderMonth { PREVIOUS, NEXT, SELECT }

    init {
//        val a = DiaryItemModel(
//            id = 0,
//            days = "12",
//            month = "Sun",
//            year = "Sun",
//            title = "오늘 비가와요. 비를 주제로\n" + "한줄 시를 써볼까요?",
//            content = "만수산 드렁칡이 얽혀진들 어떠하리 …",
//            imageUrl = null,
//            isContent = true,
//            isImage = true
//        )
//        val aa = listOf<DiaryItemModel>() + a + a + a + a
//        _diaryList.postValue(aa)
    }

    private fun initDiary(date: String) {
        AnswerUseCase(answerRepository).getAnswersDiary2(null, 30, null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                Log.e(" Success ", list.toString())
                _diaryList.postValue(list.map {
                    val dateValue: Calendar = Calendar.getInstance()
                    dateValue.set(it.year.toInt(), it.month.toInt() - 1, it.day.toInt())
                    DiaryItemModel(
                        id = it.answerId ?: 0,
                        dayOfWeek = SimpleDateFormat("EE").format(dateValue.time),
                        days = it.day,
                        month = it.month,
                        year = it.year,
                        title = it.title ?: "",
                        content = it.content ?: "",
                        imageUrl = it.imageUrl,
                        isContent = it.isContent ?: false,
                        isImage = it.isImage ?: false,
                        isLastMonthItem = false
                    )
                })

            }, { e ->
                Log.e("postSignIn e", e.toString())
            })
    }

    fun initCalender() {
        answerRepository.getAnswersDays()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e(" Success ", it.toString())
                _writeDayList.postValue(it)
            }, { e ->
                Log.e("postSignIn e", e.toString())
            })
    }

    fun setDate(date: String) {
        val dateSplit = date.split(".")
        _month.value = "${dateSplit[0]}.${dateSplit[1]}"
        val date = "${dateSplit[0]}-${dateSplit[1]}-${dateSplit[2]}"
        initDiary(date)
    }

    fun selectMonth() {
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
