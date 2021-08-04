package com.ahobsu.moti.presentation.ui.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.AnswerUseCase
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.album.model.AnswerItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class AnswersViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

    private val _diaryAnswerList = MutableLiveData<List<AnswerItemModel>>()
    val diaryAnswerList: LiveData<List<AnswerItemModel>> = _diaryAnswerList

    private val _backPressed = MutableLiveData<Unit>()
    val backPressed: LiveData<Unit> = _backPressed

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    fun initItemList(id: Int) {
        Log.e("id", id.toString())

        AnswerUseCase(answerRepository).getAnswersItemList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("it", it.toString())
                Log.e("initItemList","fffffff")

                _diaryAnswerList.postValue(
                    it.map {
                        AnswerItemModel(
                            id = it.answerId ?: 0,
                            date = it.date ?: "",
                            title = it.title ?: "",
                            content = it.content ?: "",
                            imageUrl = it.imageUrl,
                            isContent = it.isContent ?: true,
                            isImage = it.isImage ?: false
                        )
                    }
                )
            }, { e ->
                Log.e("initItemList","eeeeee")

                Log.e("e", e.toString())
            })
    }

    fun onClickBack() {
        _backPressed.value = Unit
    }

    fun onClickRewrite() {
    }
}