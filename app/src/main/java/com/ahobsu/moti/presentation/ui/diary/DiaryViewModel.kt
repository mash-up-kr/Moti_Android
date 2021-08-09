package com.ahobsu.moti.presentation.ui.diary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.AnswerUseCase
import com.ahobsu.moti.domain.entity.AnswersDiary
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

    private val _isRenewableTop = MutableLiveData<Boolean>()
    val isRenewableTop: LiveData<Boolean> = _isRenewableTop

    private val _isRenewableBottom = MutableLiveData<Boolean>()
    val isRenewableBottom: LiveData<Boolean> = _isRenewableBottom

    private val _month = MutableLiveData<String>()
    val month: LiveData<String> = _month

    private val _clickCalenderMonth = MutableLiveData<CalenderMonth>()
    val selectedCalenderMonth: LiveData<CalenderMonth> = _clickCalenderMonth

    private val _isEmpty = MutableLiveData<Boolean>(true)
    val isEmpty: LiveData<Boolean> = _isEmpty

    enum class CalenderMonth { PREVIOUS, NEXT, SELECT }

    fun initAnswersDays() {
        AnswerUseCase(answerRepository).getAnswersDays()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e(" Success ", it.toString())
                if (it.isEmpty()) {
                    _isEmpty.postValue(true)
                } else {
                    _isEmpty.postValue(false)
                    _writeDayList.postValue(it)
                }
            }, { e ->
                Log.e("postSignIn e", e.toString())
            })
    }

    private fun initDiary(date: String?) {
        AnswerUseCase(answerRepository).getAnswersDiary(null, 10, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                Log.e(" Success ", list.toString())
                if (list.isEmpty()) {
                    _isEmpty.postValue(true)
                } else {
                    if (date.isNullOrEmpty()) {
                        _diaryList.postValue(createDiaryList(list))
                    } else {
                        _diaryList.postValue(createDiaryList(list.reversed()))
                    }
                    _isEmpty.postValue(false)
                }
            }, { e ->
                Log.e("postSignIn e", e.toString())
            })
    }

    fun onScrollEvent(isTop: Boolean) {
        val limit = 10
        diaryList.value?.let {
            val date = if (isTop) it[0].date else it[it.size - 1].date
            val direction = if (isTop) 1 else 0

            AnswerUseCase(answerRepository).getAnswersDiary(direction, limit, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    Log.e(" Success ", list.toString())
                    if (list.isEmpty()) {
                        if (isTop) _isRenewableTop.postValue(false)
                        else _isRenewableBottom.postValue(false)
                    } else {
                        val item =
                            if (isTop) createDiaryList(list).reversed() + it
                            else it + createDiaryList(list).reversed()
                        _diaryList.postValue(item)
                    }
                }, { e ->
                    Log.e("postSignIn e", e.toString())
                })
        }

    }

    private fun createDiaryList(list: List<AnswersDiary>): List<DiaryItemModel> {
        return list.map {
            val dateValue: Calendar = Calendar.getInstance()
            val item = it.date?.split("-")
            val day = item?.get(2) ?: "1"
            val month = item?.get(1) ?: "1"
            val year = item?.get(0) ?: "1"
            dateValue.set(year.toInt(), month.toInt() - 1, day.toInt())

            DiaryItemModel(
                answerId = it.answerId ?: 0,
                dayOfWeek = SimpleDateFormat("EE").format(dateValue.time),
                date = it.date ?: "",
                days = day,
                month = month,
                year = year,
                title = it.title ?: "",
                content = it.content ?: "",
                imageUrl = it.imageUrl,
                isContent = it.isContent ?: false,
                isImage = it.isImage ?: false,
                isLastMonthItem = false
            )
        }
    }

    fun setDate(date: String, isToday: Boolean) {
        _isRenewableTop.value = (true)
        _isRenewableBottom.value = (true)
        val dateSplit = date.split(".")
        Log.e("setDate date", date.toString())

        if (!isToday) {
            val dateValue: Calendar = Calendar.getInstance()
            dateValue.set(dateSplit[0].toInt(), dateSplit[1].toInt() - 1, dateSplit[2].toInt())
            dateValue.run {
                add(Calendar.DATE, +1)
            }
            initDiary(SimpleDateFormat("YYYY-MM-dd").format(dateValue.time))
        } else {
            initDiary(null)
        }
        _month.value = "${dateSplit[0]}.${dateSplit[1]}"
    }

    fun setMonthDate(date: String) {
        _month.value = date
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
