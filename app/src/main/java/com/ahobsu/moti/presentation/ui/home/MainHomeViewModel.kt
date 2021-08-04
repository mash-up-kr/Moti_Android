package com.ahobsu.moti.presentation.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.AnswerUseCase
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.home.model.HomeData
import com.ahobsu.moti.presentation.ui.question.MissionActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class MainHomeViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    private val _todayAnswer = MutableLiveData<Boolean>()
    val todayAnswer: LiveData<Boolean> = _todayAnswer

    fun getHomeAnswer() {
        AnswerUseCase(answerRepository).getAnswersWeek()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getAnswersWeek ", it.toString())
                _homeData.postValue(HomeData(it.answers))
            }, { e ->
                Log.e("e", e.toString())
            })
    }

    fun checkToday(){
        AnswerUseCase(answerRepository).getAnswerToday()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                if (it) {
                    _todayAnswer.postValue(it)
                }
            }, { e ->
                Log.e("e", e.toString())
            })
    }
    fun startQuestionActivity(context: Context) {
        AnswerUseCase(answerRepository).getAnswerToday()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                if (it) {
                    _todayAnswer.postValue(it)
                } else {
                    context.startActivity(Intent(context, MissionActivity::class.java))
                }
            }, { e ->
                Log.e("e", e.toString())
            })
    }
}