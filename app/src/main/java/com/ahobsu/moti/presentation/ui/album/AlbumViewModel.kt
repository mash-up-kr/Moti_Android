package com.ahobsu.moti.presentation.ui.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.AnswerUseCase
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.album.model.AlbumItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class AlbumViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

    private val _albumList = MutableLiveData<List<AlbumItemModel>>()
    val albumList: LiveData<List<AlbumItemModel>> = _albumList

    init {
        AnswerUseCase(answerRepository).getAnswersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getAnswersList ", it.toString())
                var item = listOf<AlbumItemModel>()
                for (i in 1..it.size) {
                    item += AlbumItemModel(i, it[it.size - i].answers)
                }
                _albumList.postValue(item)
            }, { e ->
                Log.e("e", e.toString())
            })
    }
}

