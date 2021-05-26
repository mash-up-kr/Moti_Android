package com.ahobsu.moti.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.AnswerUseCase
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.question.MissionActivity
import com.ahobsu.moti.presentation.ui.main.home.model.HomeData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class MainViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    fun getHomeAnswer() {
        AnswerUseCase(answerRepository).getAnswersWeek()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ answerWeek ->
                Log.e("getAnswersWeek ", answerWeek.toString())
                _homeData.postValue(HomeData(answerWeek.today, answerWeek.answers))
            }, { e ->
                Log.e("e", e.toString())
            })
    }

    fun startQuestionActivity(context: Context) {
        context.startActivity(Intent(context, MissionActivity::class.java))
    }
}