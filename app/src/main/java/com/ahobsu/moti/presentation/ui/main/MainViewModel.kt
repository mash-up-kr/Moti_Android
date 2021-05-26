package com.ahobsu.moti.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.AnswerUseCase
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.main.model.HomeData
import com.ahobsu.moti.presentation.ui.question.MissionActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class MainViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

    private val homeData = MutableLiveData<HomeData>(HomeData(4))

    init {
        AnswerUseCase(answerRepository).getAnswersWeek()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getAnswersWeek ", it.toString())
                it.answers?.let {
                    homeData.postValue(HomeData(it.size))
                }
//                it.answers.map {
//                    it.cardPngUrl
//                }
            }, { e ->
                Log.e("e", e.toString())
            })
    }

    fun getCountCheck(int: Int): Boolean {
        homeData.value?.let {
            if (it.daysCount >= int)
                return true
        }
        return false
    }

    fun startQuestionActivity(context: Context) {
        context.startActivity(Intent(context, MissionActivity::class.java))
    }
}