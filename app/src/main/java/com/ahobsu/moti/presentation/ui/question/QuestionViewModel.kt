package com.ahobsu.moti.presentation.ui.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.MissionUseCase
import com.ahobsu.moti.domain.repository.MissionRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.main.model.QuestionItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class QuestionViewModel(private val missionRepository: MissionRepository) : BaseViewModel() {

    private val _questionList = MutableLiveData<List<QuestionItemModel>>()
    val questionList: LiveData<List<QuestionItemModel>> = _questionList

    init {
        initQuestion()
    }

    fun onClickReset(){
        initQuestion()
    }

    fun initQuestion() {
        MissionUseCase(missionRepository).getMissions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getMissions ", it.toString())
                val items = it.map {
                    QuestionItemModel(
                        id = it.id,
                        title = it.title,
                        isContent = it.isContent,
                        isImage = it.isImage
                    )
                }
                _questionList.postValue(items)
            }, { e ->
                Log.e("e", e.toString())
            })
    }
}